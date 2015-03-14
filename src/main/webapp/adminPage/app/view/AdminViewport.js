/*
 * File: app/view/AdminViewport.js
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

Ext.define('Xixixi.view.AdminViewport', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.adminviewport',

    requires: [
        'Xixixi.view.AdminViewportViewModel',
        'Xixixi.view.AdminViewportViewController',
        'Ext.toolbar.Toolbar',
        'Ext.XTemplate',
        'Ext.grid.Panel',
        'Ext.view.Table',
        'Ext.grid.column.Date',
        'Ext.grid.column.Number'
    ],

    controller: 'adminviewport',
    viewModel: {
        type: 'adminviewport'
    },
    itemId: 'adminMainView',
    margin: '',
    style: 'background-color: #EFEFEF;',
    layout: 'border',

    items: [
        {
            xtype: 'container',
            region: 'north',
            cls: 'header',
            html: '<div id="header-right"></div>',
            id: 'header',
            maxHeight: 95
        },
        {
            xtype: 'toolbar',
            region: 'north',
            border: 0,
            height: 38,
            id: 'breadcrumbs',
            items: [
                {
                    xtype: 'container',
                    height: 38,
                    html: '<span class="pageAnchorTitle">用户管理</span>',
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
                        '    </tpl>'
                    ],
                    itemSelector: '.pageAnchorTitle',
                    store: 'GetUserNameStore'
                },
                {
                    xtype: 'container',
                    height: 38,
                    html: '｜',
                    padding: '12 0 0 0'
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
            xtype: 'container',
            region: 'west',
            style: 'background-color:#EFEFEF;',
            width: 200,
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'container',
                    html: '<div id="menu_title_text">管理员操作</div>',
                    margin: '5 10 5 10'
                },
                {
                    xtype: 'container',
                    html: '<div class="menu_operator">雇员</div>',
                    id: 'menu_user',
                    margin: '0 10 0 10',
                    listeners: {
                        afterrender: 'onMenu_userAfterRender'
                    }
                },
                {
                    xtype: 'container',
                    html: '<div class="menu_operator">部门</div>',
                    id: 'menu_department',
                    margin: '0 10 0 10',
                    listeners: {
                        afterrender: 'onMenu_departmentAfterRender'
                    }
                }
            ]
        },
        {
            xtype: 'container',
            region: 'center',
            id: 'centerContainer',
            itemId: 'userlistContainer',
            margin: '5 5 5 0',
            style: 'background-color: #EFEFEF;',
            layout: 'card',
            items: [
                {
                    xtype: 'container',
                    style: 'background-color: #EFEFEF;',
                    layout: {
                        type: 'vbox',
                        align: 'stretch'
                    },
                    items: [
                        {
                            xtype: 'container',
                            html: '<div id="menu_title_text">用户管理</div>'
                        },
                        {
                            xtype: 'toolbar',
                            flex: 1,
                            border: 0,
                            cls: 'listmenutoolbar',
                            maxHeight: 30,
                            items: [
                                {
                                    xtype: 'container',
                                    html: '<div><span><img src="adminPage/images/add.gif" alt="add icon" height=15 width=15></span><span class="list_menu_text">添加</span></div>',
                                    id: 'menu_adduser',
                                    margin: '0 10 0 10',
                                    listeners: {
                                        afterrender: 'onMenu_adduserAfterRender'
                                    }
                                },
                                {
                                    xtype: 'container',
                                    html: '<div><span><img src="adminPage/images/delete.gif" alt="delete icon" height=15 width=15></span><span class="list_menu_text">删除</span></div>',
                                    id: 'menu_deleteuser',
                                    margin: '0 10 0 10',
                                    listeners: {
                                        afterrender: 'onMenu_deleteuserAfterRender'
                                    }
                                },
                                {
                                    xtype: 'container',
                                    html: '<div><span><img src="adminPage/images/refresh.gif" alt="delete icon" height=15 width=15></span><span class="list_menu_text">刷新</span></div>',
                                    id: 'menu_refreshuser',
                                    margin: '0 10 0 10',
                                    listeners: {
                                        afterrender: 'onMenu_refreshuserAfterRender'
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            flex: 1,
                            scrollable: true,
                            header: false,
                            title: 'My Grid Panel',
                            bind: {
                                store: 'UserListViewStore'
                            },
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userName',
                                    text: '用户名'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'department',
                                    text: '部门'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'role',
                                    text: '身份'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'manager',
                                    text: '上司'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'fullName',
                                    text: '姓名'
                                },
                                {
                                    xtype: 'datecolumn',
                                    dataIndex: 'createdDate',
                                    text: '创建日期',
                                    format: 'm/j/Y'
                                }
                            ],
                            listeners: {
                                itemdblclick: 'onGridpanelItemDblClick'
                            }
                        }
                    ]
                },
                {
                    xtype: 'container',
                    layout: {
                        type: 'vbox',
                        align: 'stretch'
                    },
                    items: [
                        {
                            xtype: 'container',
                            html: '<div id="menu_title_text">部门管理</div>'
                        },
                        {
                            xtype: 'toolbar',
                            flex: 1,
                            border: 0,
                            cls: 'listmenutoolbar',
                            maxHeight: 30,
                            width: 150,
                            items: [
                                {
                                    xtype: 'container',
                                    html: '<div><span><img src="adminPage/images/add.gif" alt="add icon" height=15 width=15></span><span class="list_menu_text">添加</span></div>',
                                    id: 'menu_adddepartment',
                                    margin: '0 10 0 10',
                                    listeners: {
                                        afterrender: 'onMenu_adddepartmentAfterRender'
                                    }
                                },
                                {
                                    xtype: 'container',
                                    html: '<div><span><img src="adminPage/images/delete.gif" alt="delete icon" height=15 width=15></span><span class="list_menu_text">删除</span></div>',
                                    id: 'menu_deletedepartment',
                                    margin: '0 10 0 10',
                                    listeners: {
                                        afterrender: 'onMenu_deletedepartmentAfterRender'
                                    }
                                },
                                {
                                    xtype: 'container',
                                    html: '<div><span><img src="adminPage/images/refresh.gif" alt="delete icon" height=15 width=15></span><span class="list_menu_text">刷新</span></div>',
                                    id: 'menu_refreshdepartment',
                                    margin: '0 10 0 10',
                                    listeners: {
                                        afterrender: 'onMenu_refreshdepartmentAfterRender'
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            flex: 1,
                            scrollable: true,
                            header: false,
                            bind: {
                                store: 'DepartmentListViewStore'
                            },
                            columns: [
                                {
                                    xtype: 'numbercolumn',
                                    dataIndex: 'departmentId',
                                    text: '部门编号',
                                    format: '0,000'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'departmentName',
                                    text: '名字'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'departmentDesc',
                                    text: '描述'
                                },
                                {
                                    xtype: 'numbercolumn',
                                    dataIndex: 'departmentNum',
                                    text: '部门人数',
                                    format: '0,000'
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ]

});