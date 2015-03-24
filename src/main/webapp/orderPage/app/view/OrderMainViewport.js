Ext.define('Xixixi.view.OrderMainViewport', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.ordermainviewport',

    requires: [
        'Xixixi.view.OrderMainViewportViewModel',
        'Xixixi.view.OrderMainViewportViewController',
        'Ext.XTemplate',
        'Ext.form.field.Text',
        'Ext.Img',
        'Ext.grid.Panel',
        'Ext.view.Table',
        'Ext.grid.column.Action',
        'Ext.grid.plugin.RowExpander',
        'Ext.toolbar.Paging'
    ],

    controller: 'ordermainviewport',
    viewModel: {
        type: 'ordermainviewport'
    },
    padding: '',
    style: 'background-color: #EFEFEF;',
    layout: 'border',

    items: [
        {
            xtype: 'container',
            region: 'north',
            height: 150,
            id: 'header',
            maxHeight: 97
        },
        {
            xtype: 'toolbar',
            region: 'north',
            height: 38,
            id: 'breadcrumbs',
            items: [
                {
                    xtype: 'container',
                    height: 38,
                    html: '<span class="pageAnchorTitle">订单管理</span>',
                    padding: '11 10 0 15'
                },
                {
                    xtype: 'container',
                    flex: 1
                },
                {
                    xtype: 'dataview',
                    height: 38,
                    padding: '5 10 0 0',
                    tpl: [
                        '<tpl for=".">',
                        '    <div class=".pageAnchorTitle">欢迎 {username}</div>',
                        '     </tpl>'
                    ],
                    itemSelector: '.pageAnchorTitle',
                    store: 'GetUserNameStore'
                },
                {
                    xtype: 'container',
                    height: 38,
                    html: '|'
                },
                {
                    xtype: 'container',
                    height: 38,
                    html: '<A class="pageAnchor" href="j_spring_security_logout"> Logout </A>',
                    padding: '11 10 0 0'
                }
            ]
        },
        {
            xtype: 'panel',
            region: 'west',
            padding: '',
            width: 150,
            bodyStyle: 'background-color: #EFEFEF;',
            collapsible: true,
            title: '导航',
            titleAlign: 'right',
            items: [
                {
                    xtype: 'container',
                    html: '<div class="menu_operator">待处理订单</div>',
                    id: 'menu_orderwaiting'
                },
                {
                    xtype: 'container',
                    html: '<div class="menu_operator">已处理订单</div>',
                    id: 'menu_processed'
                },
                {
                    xtype: 'container',
                    html: '<div class="menu_operator">已取消订单</div>',
                    id: 'menu_canceled'
                }
            ]
        },
        {
            xtype: 'container',
            region: 'center',
            scrollable: true,
            items: [
                {
                    xtype: 'panel',
                    height: 90,
                    padding: 5,
                    collapsed: false,
                    collapsible: true,
                    title: '查找订单',
                    titleAlign: 'right',
                    titleCollapse: true,
                    layout: {
                        type: 'vbox',
                        align: 'stretch'
                    },
                    items: [
                        {
                            xtype: 'container',
                            flex: 1,
                            reference: 'searchContainerRef',
                            layout: {
                                type: 'hbox',
                                align: 'stretch'
                            },
                            items: [
                                {
                                    xtype: 'textfield',
                                    itemId: 'cellPhoneField',
                                    padding: '10 10 10 10',
                                    fieldLabel: '下单手机/座机号',
                                    name: 'cellPhone'
                                },
                                {
                                    xtype: 'textfield',
                                    itemId: 'orderIdField',
                                    padding: '10 10 10 10',
                                    width: 300,
                                    fieldLabel: '订单号',
                                    labelWidth: 45,
                                    name: 'orderId'
                                },
                                {
                                    xtype: 'image',
                                    flex: 1,
                                    id: 'searchIcon',
                                    margin: '10 10 10 10 ',
                                    maxWidth: 25,
                                    width: '',
                                    src: 'orderPage/images/find.png'
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'container',
                    reference: 'orderCenterContainerRef',
                    id: 'orderCenterContainer',
                    itemId: 'orderCenterContainer',
                    layout: 'card',
                    items: [
                        {
                            xtype: 'gridpanel',
                            reference: 'waitingOrderGridRef',
                            height: 600,
                            id: 'waitingOrderGrid',
                            padding: 5,
                            width: 300,
                            collapsible: false,
                            reserveScrollbar: true,
                            store: 'OrderListStore',
                            columns: [
                                {
                                    xtype: 'actioncolumn',
                                    align: 'center',
                                    text: '联系客户',
                                    items: [
                                        {
                                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                                var oId = record.data.orderId;
                                                Ext.Ajax.timeout = 40000;
                                                Ext.Ajax.Request({
                                                    url : 'cancelOrder.action',
                                                    method : 'POST',
                                                    params :{
                                                        orderId : oId
                                                    },
                                                    success: function(response){
                                                        var respText = Ext.JSON.decode(response.responseText);
                                                        if(respText.success){
                                                            Ext.Msg.alert('成功',respText.message);

                                                            var store = Ext.data.StoreManager.lookup('OrderListStore');
                                                            store.removeAll();
                                                            store.getProxy().extraParams = {orderStatus : 1};
                                                            store.load();
                                                        }else{
                                                            Ext.Msg.alert('失败',respText.message);
                                                        }
                                                    },
                                                    failure : function(response){
                                                        Ext.Msg.alert('失败','网络可能有问题');
                                                    }
                                                });
                                            },
                                            icon: 'orderPage/images/customer.png',
                                            tooltip: '联系客户'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'actioncolumn',
                                    width: '',
                                    align: 'center',
                                    text: '取消订单',
                                    items: [
                                        {
                                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                                console.log(view);
                                                console.log(rowIndex);
                                                console.log(item);
                                                console.log(e);
                                                console.log(record);
                                                console.log(row);
                                            },
                                            icon: 'orderPage/images/error.png',
                                            tooltip: '取消订单'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'orderId',
                                    text: '订单号'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'orderBy',
                                    text: '下单客户'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'csRep',
                                    text: '联系客服'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'pickedUpBy',
                                    text: '取单快递'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'deliveredBy',
                                    text: '送单快递'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'price',
                                    text: '总价'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'actualIncome',
                                    text: '实收'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'orderStatus',
                                    text: '订单状态'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'lastUpdatedBy',
                                    text: '最后更新'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'lastUpdateTime',
                                    formatter: 'date("Y-m-d H:i:s")',
                                    text: '最后更新时间'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'comments',
                                    text: '备注'
                                }
                            ],
                            plugins: [
                                {
                                    ptype: 'rowexpander',
                                    rowBodyTpl: [
                                        '<div>地址: {address.state} {address.city} {address.district} {address.street} {address.streetNum} {address.zipcode}</div>',
                                        '<div>联系方式: {address.fullName} {address.phoneNumber}</div>',
                                        '<div>类别: <tpl for="orderItems"> {itemName} {amount} {pricePerItem}</tpl></div>'
                                    ]
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    afterPageText: '页，共 {0}页',
                                    beforePageText: '第',
                                    displayInfo: true,
                                    displayMsg: '展示 {2}条中的{0} - {1} ',
                                    emptyMsg: '没有记录',
                                    firstText: '第一页',
                                    lastText: '最后一页',
                                    nextText: '下一页',
                                    prevText: '上一页',
                                    refreshText: '刷新',
                                    store: 'OrderListStore'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            reference: 'processedOrderGridRef',
                            height: 600,
                            id: 'processedOrderGrid',
                            padding: 5,
                            scrollable: true,
                            width: 300,
                            collapsible: false,
                            store: 'OrderListStore',
                            columns: [
                                {
                                    xtype: 'actioncolumn',
                                    align: 'center',
                                    text: '联系客户',
                                    items: [
                                        {
                                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                                console.log(view);
                                                console.log(rowIndex);
                                                console.log(item);
                                                console.log(e);
                                                console.log(record);
                                                console.log(row);
                                            },
                                            icon: 'orderPage/images/customer.png',
                                            tooltip: '联系客户'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'actioncolumn',
                                    width: '',
                                    align: 'center',
                                    text: '取消订单',
                                    items: [
                                        {
                                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                                console.log(view);
                                                console.log(rowIndex);
                                                console.log(item);
                                                console.log(e);
                                                console.log(record);
                                                console.log(row);
                                            },
                                            icon: 'orderPage/images/error.png',
                                            tooltip: '取消订单'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'orderId',
                                    text: '订单号'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'orderBy',
                                    text: '下单客户'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'csRep',
                                    text: '联系客服'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'pickedUpBy',
                                    text: '取单快递'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'deliveredBy',
                                    text: '送单快递'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'price',
                                    text: '总价'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'actualIncome',
                                    text: '实收'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'orderStatus',
                                    text: '订单状态'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'lastUpdatedBy',
                                    text: '最后更新'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'lastUpdateTime',
                                    text: '最后更新时间'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'comments',
                                    text: '备注'
                                }
                            ],
                            plugins: [
                                {
                                    ptype: 'rowexpander',
                                    rowBodyTpl: [
                                        '<div>地址: {address.state} {address.city} {address.district} {address.street} {address.streetNum} {address.zipcode}</div>',
                                        '<div>联系方式: {address.fullName} {address.phoneNumber}</div>',
                                        '<div>类别: <tpl for="orderItems"> {itemName} {amount} {pricePerItem}</tpl></div>'
                                    ]
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    afterPageText: '页，共 {0}页',
                                    beforePageText: '第',
                                    displayInfo: true,
                                    displayMsg: '展示 {2}条中的{0} - {1} ',
                                    emptyMsg: '没有记录',
                                    firstText: '第一页',
                                    lastText: '最后一页',
                                    nextText: '下一页',
                                    prevText: '上一页',
                                    refreshText: '刷新',
                                    store: 'OrderListStore'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            reference: 'canceledOrderGridRef',
                            height: 600,
                            id: 'canceledOrderGrid',
                            padding: 5,
                            scrollable: true,
                            width: 300,
                            collapsible: false,
                            store: 'OrderListStore',
                            columns: [
                                {
                                    xtype: 'actioncolumn',
                                    align: 'center',
                                    text: '联系客户',
                                    items: [
                                        {
                                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                                console.log(view);
                                                console.log(rowIndex);
                                                console.log(item);
                                                console.log(e);
                                                console.log(record);
                                                console.log(row);
                                            },
                                            icon: 'orderPage/images/customer.png',
                                            tooltip: '联系客户'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'actioncolumn',
                                    width: '',
                                    align: 'center',
                                    text: '取消订单',
                                    items: [
                                        {
                                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                                console.log(view);
                                                console.log(rowIndex);
                                                console.log(item);
                                                console.log(e);
                                                console.log(record);
                                                console.log(row);
                                            },
                                            icon: 'orderPage/images/error.png',
                                            tooltip: '取消订单'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'orderId',
                                    text: '订单号'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'orderBy',
                                    text: '下单客户'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'csRep',
                                    text: '联系客服'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'pickedUpBy',
                                    text: '取单快递'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'deliveredBy',
                                    text: '送单快递'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'price',
                                    text: '总价'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'actualIncome',
                                    text: '实收'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'orderStatus',
                                    text: '订单状态'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'lastUpdatedBy',
                                    text: '最后更新'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'lastUpdateTime',
                                    text: '最后更新时间'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'comments',
                                    text: '备注'
                                }
                            ],
                            plugins: [
                                {
                                    ptype: 'rowexpander',
                                    rowBodyTpl: [
                                        '<div>地址: {address.state} {address.city} {address.district} {address.street} {address.streetNum} {address.zipcode}</div>',
                                        '<div>联系方式: {address.fullName} {address.phoneNumber}</div>',
                                        '<div>类别: <tpl for="orderItems"> {itemName} {amount} {pricePerItem}</tpl></div>'
                                    ]
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    afterPageText: '页，共 {0}页',
                                    beforePageText: '第',
                                    displayInfo: true,
                                    displayMsg: '展示 {2}条中的{0} - {1} ',
                                    emptyMsg: '没有记录',
                                    firstText: '第一页',
                                    lastText: '最后一页',
                                    nextText: '下一页',
                                    prevText: '上一页',
                                    refreshText: '刷新',
                                    store: 'OrderListStore'
                                }
                            ]
                        }
                    ],
                    listeners: {
                        _afterrender0: 'onOrderCenterContainerAfterRender',
                        _afterrender1: 'onOrderCenterContainerAfterRender1',
                        _afterrender2: 'onOrderCenterContainerAfterRender2',
                        _afterrender3: 'onOrderCenterContainerAfterRender3',
                        afterrender: function() {
                            var me = this,
                                args = Ext.toArray(arguments, 0, -1);
                            args.unshift('_afterrender0');
                            me.fireEvent.apply(me, args);
                            args[0] = '_afterrender1';
                            me.fireEvent.apply(me, args);
                            args[0] = '_afterrender2';
                            me.fireEvent.apply(me, args);
                            args[0] = '_afterrender3';
                            me.fireEvent.apply(me, args);
                        }
                    }
                }
            ]
        }
    ]

});