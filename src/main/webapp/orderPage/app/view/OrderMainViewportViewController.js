/*
 * File: app/view/OrderMainViewportViewController.js
 *
 * This file was generated by Sencha Architect version 3.2.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 5.1.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 5.1.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('Xixixi.view.OrderMainViewportViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.ordermainviewport',

    onGridpanelEdit: function(editor,e) {
        var record = e.record;
        var price = record.get('amount')*record.get('unitPrice');
        record.set('totalPrice', price);
        record.commit();
    },

    onButtonClick: function(button, e, eOpts) {
        var form = button.up('form').getForm();
        // var grid = form.down('gridpanel');
        var store = Ext.data.StoreManager.lookup('CategoryPriceStore');

        if (form.isValid()) {
            var record = store.data.items;
            
            var orders = '\"orderItems\" : [';
            var orderCount = 0;
            for(var i = 0; i < store.getCount();++i){
                var currentRecord = record[i].data;
               
                if(currentRecord.amount > 0){
                	 ++orderCount;
                	if(orderCount > 1)
                	orders += ',';
                	var currentOrder = '{\"itemId\" : \"' + currentRecord.itemId +'\", \"amount\" : \"' + currentRecord.amount +'\", \"totalPrice\" : \"' +currentRecord.totalPrice +'\" }';
                	orders += currentOrder;
                	
                }
                
                var recordJson = Ext.JSON.encode(currentRecord);
                //console.log(recordJson);
            }
            orders += '],';
            var jsonData = Ext.JSON.encode(form.getValues());
            var start = jsonData.indexOf('{');
            jsonData = jsonData.substr(start+1);
            jsonData = '{' + orders + jsonData;
            console.log(jsonData);
            Ext.Ajax.setTimeout(40000);
            Ext.Ajax.request({
                url : 'createOrderForCustomer.action',
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                params : jsonData,
                success : function(response) {

                    var responseText = Ext.JSON
                    .decode(response.responseText);
                    if (responseText.success) {
                        var store = Ext.data.StoreManager
                        .lookup('CategoryPriceStore');
                        store.reload();
                        console.log(form);
                        form.reset();
                        if (Ext.MessageBox) {
                            Ext.MessageBox.buttonText = {
                                ok : "确定",
                                cancel : "取消",
                                yes : "是",
                                no : "否"
                            };
                        }
                        Ext.Msg.show({
                            title : '创建订单成功！',
                            msg : responseText.message,
                            buttions : Ext.Msg.OK,
                            icon : Ext.Msg.INFO
                        });
                    } else {
                        if (Ext.MessageBox) {
                            Ext.MessageBox.buttonText = {
                                ok : "确定",
                                cancel : "取消",
                                yes : "是",
                                no : "否"
                            };
                        }
                        Ext.Msg.show({
                            title : '服务器失败',
                            msg : responseText.message,
                            buttions : Ext.Msg.OK,
                            icon : Ext.Msg.ERROR
                        });
                    }

                },
                failure : function(form, action) {
                    if (Ext.MessageBox) {
                        Ext.MessageBox.buttonText = {
                            ok : "确定",
                            cancel : "取消",
                            yes : "是",
                            no : "否"
                        };
                    }

                    if (action.failureType === Ext.form.action.Action.CLIENT_INVALID) {

                        Ext.Msg.show({
                            title : '失败',
                            msg : '填写的信息不合规范',
                            buttons : Ext.Msg.OK,
                            icon : Ext.Msg.ERROR
                        });

                    }

                    if (action.failureType === Ext.form.action.Action.CONNECT_FAILURE) {

                        Ext.Msg
                        .show({
                            title : '失败',
                            msg : '状态:'+ action.response.status+ ': '+ action.response.statusText,
                            buttons : Ext.Msg.OK,
                            icon : Ext.Msg.ERROR
                        });

                    }

                    if (action.failureType === Ext.form.action.Action.SERVER_INVALID) {

                        // server responded with success = false
                        Ext.Msg
                        .show({
                            title : '失败',
                            msg : action.result.errormsg,
                            buttons : Ext.Msg.OK,
                            icon : Ext.Msg.ERROR
                        });

                    }

                }

            });

        }

    },

    onOrderCenterContainerAfterRender: function(component, eOpts) {


        Ext.get('menu_orderwaiting').on('click',function(){
            var container = this.lookupReference('orderCenterContainerRef');
            container.getLayout().setActiveItem(0);
            var grid = this.lookupReference('waitingOrderGridRef');
            var store = grid.store;
            store.removeAll();
            store.getProxy().url = 'getAllOrdersForCS.action';
            store.getProxy().extraParams = {orderStatus : 1};
            store.loadPage(1);

        },this);

    },

    onOrderCenterContainerAfterRender1: function(component, eOpts) {

        Ext.get('menu_processed').on('click',function(){
            var container = this.lookupReference('orderCenterContainerRef');
            container.getLayout().setActiveItem(1);
            var grid = this.lookupReference('processedOrderGridRef');
            var store = grid.store;
            store.removeAll();
            store.getProxy().url = 'getAllOrdersForCS.action';
            store.getProxy().extraParams = {orderStatus : 2};
            store.loadPage(1);

        },this);

    },

    onOrderCenterContainerAfterRender2: function(component, eOpts) {

        Ext.get('menu_canceled').on('click',function(){
            var container = this.lookupReference('orderCenterContainerRef');
            container.getLayout().setActiveItem(2);
            var grid = this.lookupReference('canceledOrderGridRef');
            var store = grid.store;
            store.removeAll();
            store.getProxy().url = 'getAllOrdersForCS.action';
            store.getProxy().extraParams = {orderStatus : 3};
            store.loadPage(1);

        },this);

    },

    onOrderCenterContainerAfterRender3: function(component, eOpts) {

        Ext.get('searchIcon').on('click',function(){
            console.log(this);
            var currentGrid = this.lookupReference('orderCenterContainerRef').getLayout().getActiveItem().getReference();
            //console.log(currentGrid);
            var orderStatus;
            if(currentGrid == "waitingOrderGridRef")
            orderStatus = 1;
            else if(currentGrid == "processedOrderGridRef")
            orderStatus = 2;
            else
            orderStatus = 3;
            var searchContainer = this.lookupReference('searchContainerRef');
            //console.log(searchContainer);
            var cellPhone = searchContainer.down('#cellPhoneField').getValue();
            var orderId = searchContainer.down('#orderIdField').getValue();
            if(cellPhone || orderId){

                var store = Ext.data.StoreManager.lookup('OrderListStore');
                store.getProxy().url= 'searchOrder.action';
                store.getProxy().extraParams = {orderStatus : orderStatus,
                    cellPhone : cellPhone,
                    orderId : orderId
                };
                store.removeAll();
                store.loadPage(1);
            }


        },this);

    },

    onOrderCenterContainerAfterRender4: function(component, eOpts) {
        Ext.get('menu_createorder').on('click',function(){
            var container = this.lookupReference('orderCenterContainerRef');
            container.getLayout().setActiveItem(3);
        },this);
    }

});
