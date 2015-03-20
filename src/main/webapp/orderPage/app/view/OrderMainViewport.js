/*
 * File: app/view/OrderMainViewport.js
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

Ext.define('Xixixi.view.OrderMainViewport', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.ordermainviewport',

    requires: [
        'Xixixi.view.OrderMainViewportViewModel',
        'Xixixi.view.OrderMainViewportViewController',
        'Ext.toolbar.Toolbar',
        'Ext.XTemplate',
        'Ext.grid.Panel',
        'Ext.view.Table',
        'Ext.grid.column.Action',
        'Ext.grid.plugin.RowExpander'
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
            items: [
                {
                    xtype: 'panel',
                    items: [
                        {
                            xtype: 'panel',
                            height: 150,
                            padding: 5,
                            collapsed: true,
                            collapsible: true,
                            title: '查找订单',
                            titleAlign: 'right',
                            titleCollapse: true
                        }
                    ]
                },
                {
                    xtype: 'container',
                    id: 'orderCenterContainer',
                    itemId: 'orderCenterContainer',
                    layout: 'card',
                    items: [
                        {
                            xtype: 'gridpanel',
                            id: 'waitingOrderGrid',
                            padding: 5,
                            collapsible: false,
                            title: '',
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
                                            icon: '/orderPage/images/customer.png',
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
                                            icon: '/orderPage/images/error.png',
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
                                    dataIndex: 'lastUpdateTime',
                                    text: '最后更新'
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
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            id: 'processedOrderGrid',
                            padding: 5,
                            collapsible: false,
                            title: '',
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
                                            icon: '/orderPage/images/customer.png',
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
                                            icon: '/orderPage/images/error.png',
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
                                    dataIndex: 'lastUpdateTime',
                                    text: '最后更新'
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
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            id: 'canceledOrderGrid',
                            padding: 5,
                            collapsible: false,
                            title: '',
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
                                            icon: '/orderPage/images/customer.png',
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
                                            icon: '/orderPage/images/error.png',
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
                                    dataIndex: 'lastUpdateTime',
                                    text: '最后更新'
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
                            ]
                        }
                    ],
                    listeners: {
                        _afterrender0: 'onOrderCenterContainerAfterRender',
                        _afterrender1: 'onOrderCenterContainerAfterRender1',
                        _afterrender2: 'onOrderCenterContainerAfterRender2',
                        afterrender: function() {
                            var me = this,
                                args = Ext.toArray(arguments, 0, -1);
                            args.unshift('_afterrender0');
                            me.fireEvent.apply(me, args);
                            args[0] = '_afterrender1';
                            me.fireEvent.apply(me, args);
                            args[0] = '_afterrender2';
                            me.fireEvent.apply(me, args);
                        }
                    }
                }
            ]
        }
    ]

});