/*
 * File: app/view/AdminViewportViewController.js
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

Ext.define('Xixixi.view.AdminViewportViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.adminviewport',

    onMenu_adduserAfterRender: function(component, eOpts) {
        Ext.get('menu_adduser').on('click',function(){
            var addUserWin = Ext.get('addUserWindow');

            if(!addUserWin){
                Ext.create('Xixixi.view.AddUser').show();
            }
        },this);
    },

    onGridpanelItemDblClick: function(dataview, record, item, index, e, eOpts) {

    },

    onMenu_adddepartmentAfterRender: function(component, eOpts) {
        Ext.get('menu_adddepartment').on('click',function(){
            var addDepartmentWin = Ext.get('addDepartmentWindow');
            if(!addDepartmentWin){
                Ext.create('Xixixi.view.AddDepartment').show();
            }

        },this);
    },

    onCenterContainerAfterRender: function(component, eOpts) {
        Ext.get('menu_deleteuser').on('click',function(){
            var grid = Ext.getCmp('userlistgrid');
            var record = grid.getSelectionModel().getSelection()[0];
            if(record){
                var userName = record.data.userName;
                var currentUser = Ext.data.StoreManager.lookup('GetUserNameStore').first();
                if(currentUser)
                {
                    var currentUserName = currentUser.get('username');
                    if(userName == currentUserName){
                        Ext.Msg.show({
                            title:'警告',
                            msg:'不能删除自己',
                            buttons: Ext.Msg.OK,
                            icon: Ext.Msg.WARNING
                        });
                    }
                    else{
                        Ext.MessageBox.confirm('删除用户?','确定要删除用户, 用户名是 = '+userName+' ?',

                        function(e){
                            if(e=='yes'){
                                Ext.Ajax.timeout = 40000; // 40 seconds

                                Ext.Ajax.request({

                                    url: 'admin/userDestory.action',
                                    method : 'POST',
                                    params :  { userName: userName },
                                    success: function(response) {

                                        var respText = Ext.JSON.decode(response.responseText);

                                        if(respText.success){

                                            Ext.Msg.alert('成功', respText.message);
                                            var userListStore = Ext.data.StoreManager.lookup("UserListViewStore");
                                            userListStore.remove(record);

                                        }else{

                                            Ext.Msg.show({
                                                title:'失败',
                                                msg:respText.message,
                                                buttons: Ext.Msg.OK,
                                                icon: Ext.Msg.ERROR
                                            });

                                        }

                                    },

                                    failure: function(form, action){

                                        if (action.failureType === Ext.form.action.Action.CLIENT_INVALID) {

                                            Ext.Msg.show({
                                                title:'失败',
                                                msg: '纪录可能有不合格字段',
                                                buttons: Ext.Msg.OK,
                                                icon: Ext.Msg.ERROR
                                            });

                                        }

                                        if (action.failureType === Ext.form.action.Action.CONNECT_FAILURE){

                                            Ext.Msg.show({
                                                title:'错误',
                                                msg: '状态:'+action.response.status+': '+action.response.statusText,
                                                buttons: Ext.Msg.OK,
                                                icon: Ext.Msg.ERROR
                                            });

                                        }

                                        if (action.failureType === Ext.form.action.Action.SERVER_INVALID){

                                            // server responded with success = false
                                            Ext.Msg.show({
                                                title:'服务器无效',
                                                msg: action.result.errormsg,
                                                buttons: Ext.Msg.OK,
                                                icon: Ext.Msg.ERROR
                                            });

                                        }

                                    }
                                });
                            }
                        }
                        );
                    }
                }
            }

        },this);
    },

    onCenterContainerAfterRender1: function(component, eOpts) {
        Ext.get('menu_user').on('click',function(){
            Ext.getCmp('centerContainer').getLayout().setActiveItem(0);

            var userListStore = Ext.data.StoreManager.lookup('UserListViewStore');
            //    console.log(this);
            console.log(userListStore);
            userListStore.removeAll();
            userListStore.load();
        },this);
    },

    onCenterContainerAfterRender2: function(component, eOpts) {
        Ext.get('menu_department').on('click',function(){
            Ext.getCmp('centerContainer').getLayout().setActiveItem(1);
            var departmentListStore = Ext.data.StoreManager.lookup('DepartmentListViewStore');
            console.log(departmentListStore);
            departmentListStore.removeAll();
            departmentListStore.load();
        },this);
    },

    onCenterContainerAfterRender3: function(component, eOpts) {

        Ext.get('menu_refreshuser').on('click',function(){

            var userListStore = Ext.data.StoreManager.lookup('UserListViewStore');

            userListStore.removeAll();
            userListStore.load();

        },this);
    },

    onCenterContainerAfterRender4: function(component, eOpts) {
        Ext.get('menu_refreshdepartment').on('click',function(){
            var departmentListStore = Ext.data.StoreManager.lookup('DepartmentListViewStore');
            departmentListStore.removeAll();
            departmentListStore.load();

        },this);
    },

    onCenterContainerAfterRender5: function(component, eOpts) {

    	Ext.get('menu_deletedepartment').on('click',function(){
    	    var grid = Ext.getCmp('departmentlistgrid');
    	    var record = grid.getSelectionModel().getSelection()[0];
    	    if(record){
    	        var depId = record.data.departmentId;
    	        console.log(depId);
    	         Ext.MessageBox.confirm('删除部门?','确定要删除部门, 部门编号是 = '+depId+' ?',

    	          function(e){
    	                    if(e=='yes'){
    	                        Ext.Ajax.timeout = 40000; // 40 seconds

    	                        Ext.Ajax.request({

    	                            url: 'admin/depDestory.action',
    	                            method : 'POST',
    	                            params :  { depId: depId },
    	                            success: function(response) {

    	                                var respText = Ext.JSON.decode(response.responseText);

    	                                if(respText.success){

    	                                    Ext.Msg.alert('成功', respText.message);
    	                                    var depListStore = Ext.data.StoreManager.lookup("DepartmentListViewStore");
    	                                    depListStore.remove(record);

    	                                }else{

    	                                    Ext.Msg.show({
    	                                        title:'失败',
    	                                        msg:respText.message,
    	                                        buttons: Ext.Msg.OK,
    	                                        icon: Ext.Msg.ERROR
    	                                    });

    	                                }

    	                            },

    	                            failure: function(form, action){

    	                                if (action.failureType === Ext.form.action.Action.CLIENT_INVALID) {

    	                                    Ext.Msg.show({
    	                                        title:'失败',
    	                                        msg: '纪录可能有不合格字段',
    	                                        buttons: Ext.Msg.OK,
    	                                        icon: Ext.Msg.ERROR
    	                                    });

    	                                }

    	                                if (action.failureType === Ext.form.action.Action.CONNECT_FAILURE){

    	                                    Ext.Msg.show({
    	                                        title:'错误',
    	                                        msg: '状态:'+action.response.status+': '+action.response.statusText,
    	                                        buttons: Ext.Msg.OK,
    	                                        icon: Ext.Msg.ERROR
    	                                    });

    	                                }

    	                                if (action.failureType === Ext.form.action.Action.SERVER_INVALID){

    	                                    // server responded with success = false
    	                                    Ext.Msg.show({
    	                                        title:'服务器无效',
    	                                        msg: action.result.errormsg,
    	                                        buttons: Ext.Msg.OK,
    	                                        icon: Ext.Msg.ERROR
    	                                    });

    	                                }

    	                            }
    	                        });
    	                    }
    	                }
    	                );
    	            }
    	        
    	    

    	},this);
    }

});
