Ext.define('Xixixi.view.AddUser', {
    extend: 'Ext.window.Window',
    alias: 'widget.adduser',

    requires: [
        'Xixixi.view.AddUserViewModel',
        'Ext.form.Panel',
        'Ext.XTemplate',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Date',
        'Ext.button.Button'
    ],

    viewModel: {
        type: 'adduser'
    },
    autoShow: true,
    height: 475,
    hidden: false,
    id: 'addUserWindow',
    minHeight: 450,
    resizable: false,
    width: 560,
    title: '添加用户',
    defaultListenerScope: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'form',
            flex: 1,
            id: 'addUserForm',
            itemId: 'addUserForm',
            margin: '10 10 10 10',
            bodyPadding: 10,
            bodyStyle: 'background:#DFE8F6;\r\nborder:1px solid #B5B8C8',
            header: false,
            title: 'My Form',
            items: [
                {
                    xtype: 'textfield',
                    validator: function(value) {
                        //if(value.length >= 3){
                        //var result = this.validateUserName(value);

                        //  return result;
                        return true;
                        //}
                    },
                    anchor: '100%',
                    id: 'username',
                    itemId: 'username',
                    maxWidth: 500,
                    modelValidation: true,
                    width: 500,
                    afterLabelTextTpl: [
                        '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>'
                    ],
                    fieldLabel: '用户名',
                    labelAlign: 'right',
                    labelPad: 10,
                    labelWidth: 120,
                    name: 'userName',
                    validateOnChange: false,
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    regex: /^[a-z0-9_]{3,15}$/,
                    regexText: '用户名必须由3-15个大小写字母，数字或下划线组成',
                    listeners: {
                        blur: 'onUsernameBlur'
                    }
                },
                {
                    xtype: 'textfield',
                    anchor: '100%',
                    id: 'password',
                    itemId: 'password',
                    maxWidth: 500,
                    width: 500,
                    afterLabelTextTpl: [
                        '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>'
                    ],
                    fieldLabel: '密码',
                    labelAlign: 'right',
                    labelPad: 10,
                    labelWidth: 120,
                    name: 'passWord',
                    inputType: 'password',
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    regex: /((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})/,
                    regexText: '密码必须长6-20，包含一个大写字母，一个小写字母，一个数字合一个特殊字符（“@#$%”）'
                },
                {
                    xtype: 'textfield',
                    validator: function(value) {
                        var password = Ext.getCmp('password').getValue();

                        if (password == value)
                        return true;
                        else
                        return "密码不匹配";
                    },
                    anchor: '100%',
                    id: 'password_repeat',
                    itemId: 'password_repeat',
                    maxWidth: 500,
                    afterLabelTextTpl: [
                        '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>'
                    ],
                    fieldLabel: '确认密码',
                    labelAlign: 'right',
                    labelPad: 10,
                    labelWidth: 120,
                    name: 'passwordRepeat',
                    inputType: 'password',
                    allowBlank: false,
                    allowOnlyWhitespace: false,
                    regex: /((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})/,
                    regexText: '密码必须长6-20，包含一个大写字母，一个小写字母，一个数字合一个特殊字符（“@#$%”）'
                },
                {
                    xtype: 'combobox',
                    anchor: '100%',
                    id: 'departmentcombo',
                    itemId: 'departmentcombo',
                    maxWidth: 500,
                    width: 500,
                    afterLabelTextTpl: [
                        '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>'
                    ],
                    fieldLabel: '部门',
                    labelAlign: 'right',
                    labelPad: 10,
                    labelWidth: 120,
                    name: 'deparment',
                    allowBlank: false,
                    editable: false,
                    displayField: 'name',
                    forceSelection: true,
                    queryMode: 'local',
                    store: 'DepartmentListStore',
                    valueField: 'attribute',
                    listeners: {
                        change: 'onDepartmentcomboChange',
                        focus: 'onDepartmentcomboFocus'
                    }
                },
                {
                    xtype: 'combobox',
                    anchor: '100%',
                    id: 'employeerolecombo',
                    itemId: 'employeerolecombo',
                    maxWidth: 500,
                    width: 500,
                    afterLabelTextTpl: [
                        '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>'
                    ],
                    fieldLabel: '员工级别',
                    labelAlign: 'right',
                    labelPad: 10,
                    labelWidth: 120,
                    name: 'employeerole',
                    allowBlank: false,
                    editable: false,
                    displayField: 'name',
                    forceSelection: true,
                    queryMode: 'local',
                    store: 'EmployeeRoleListStore',
                    valueField: 'attribute',
                    listeners: {
                        change: 'onEmployeerolecomboChange',
                        focus: 'onEmployeerolecomboFocus'
                    }
                },
                {
                    xtype: 'combobox',
                    anchor: '100%',
                    id: 'accessrolecombo',
                    itemId: 'accessrolecombo',
                    maxWidth: 500,
                    width: 500,
                    afterLabelTextTpl: [
                        '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>'
                    ],
                    fieldLabel: '员工权限',
                    labelAlign: 'right',
                    labelPad: 10,
                    labelWidth: 120,
                    name: 'accessrole',
                    allowBlank: false,
                    editable: false,
                    displayField: 'name',
                    forceSelection: true,
                    queryMode: 'local',
                    store: 'AccessRoleListStore',
                    listeners: {
                        focus: 'onAccessrolecomboFocus',
                        change: 'onAccessrolecomboChange'
                    }
                },
                {
                    xtype: 'combobox',
                    anchor: '100%',
                    id: 'managercombo',
                    itemId: 'managercombo',
                    maxWidth: 500,
                    width: 500,
                    afterLabelTextTpl: [
                        '<span style="color:red;font-weight:bold" data-qtip="必填">*</span>'
                    ],
                    fieldLabel: '上司',
                    labelAlign: 'right',
                    labelPad: 10,
                    labelWidth: 120,
                    name: 'reportmanager',
                    editable: false,
                    displayField: 'name',
                    queryMode: 'local',
                    store: 'ManagerListStore',
                    listeners: {
                        focus: 'onManagercomboFocus',
                        change: 'onManagercomboChange'
                    }
                },
                {
                    xtype: 'textfield',
                    anchor: '100%',
                    id: 'fullname',
                    itemId: 'fullname',
                    maxWidth: 500,
                    width: 500,
                    fieldLabel: '全名',
                    labelAlign: 'right',
                    labelPad: 10,
                    labelWidth: 120,
                    name: 'fullname'
                },
                {
                    xtype: 'datefield',
                    anchor: '100%',
                    id: 'startdate',
                    itemId: 'startdate',
                    maxWidth: 500,
                    width: 500,
                    fieldLabel: '入职时间',
                    labelAlign: 'right',
                    labelPad: 10,
                    labelWidth: 120,
                    editable: false,
                    submitFormat: 'Y-m-d'
                },
                {
                    xtype: 'container',
                    height: 30,
                    margin: '0 0 10 0',
                    layout: {
                        type: 'hbox',
                        align: 'stretch',
                        pack: 'center'
                    },
                    items: [
                        {
                            xtype: 'button',
                            flex: 1,
                            height: '',
                            id: 'addusersubmit',
                            itemId: 'addusersubmit',
                            margin: '0 10 0 0',
                            maxWidth: 100,
                            width: 100,
                            text: '提交',
                            listeners: {
                                click: 'onAddusersubmitClick'
                            }
                        },
                        {
                            xtype: 'button',
                            flex: 1,
                            id: 'adduserreset',
                            itemId: 'adduserreset',
                            margin: '0 0 0 10',
                            maxWidth: 100,
                            width: 100,
                            text: '重置',
                            listeners: {
                                click: 'onAdduserresetClick'
                            }
                        }
                    ]
                }
            ]
        }
    ],

    onUsernameBlur: function(component, event, eOpts) {
        var value = component.getValue();
        this.validateUserName(value);


    },

    onDepartmentcomboChange: function(field, newValue, oldValue, eOpts) {

    },

    onDepartmentcomboFocus: function(component, event, eOpts) {
        var store = Ext.data.StoreManager.lookup('DepartmentListStore');
        store.setProxy({
            type: 'ajax',
            url: 'getAllDepartment.action',
            reader: {
                type: 'json',
                rootProperty: 'results'
            },
            listeners: {
                exception:{
                    fn:function(server,response,operation,options){
                        var result = Ext.JSON.decode(response.responseText);
                        Ext.getCmp('fullname').focus();
                        Ext.MessageBox.show({
                            title: '服务器出错',
                            msg: result.message,
                            icon: Ext.MessageBox.ERROR,
                            buttons: Ext.Msg.OK

                        });
                    }
                }
            }
        });
        store.load({
            scope: this,
            callback: function(records, operation,success){
                if(records.length === 0){
                    Ext.getCmp('fullname').focus();
                    Ext.Msg.alert('提示','没有可选部门，请先加入部门！');
                }
            }
        });
    },

    onEmployeerolecomboChange: function(field, newValue, oldValue, eOpts) {

    },

    onEmployeerolecomboFocus: function(component, event, eOpts) {
        var store = Ext.data.StoreManager.lookup('EmployeeRoleListStore');
        store.setProxy({
                    type: 'ajax',
                    url: 'getAllEmployeeRole.action',
                    reader: {
                        type: 'json',
                        rootProperty: 'results'
                    },
                    listeners: {
                        exception:{
                            fn:function(server,response,operation,options){
                                var result = Ext.JSON.decode(response.responseText);
                                Ext.getCmp('fullname').focus();
                                Ext.MessageBox.show({
                                    title: '服务器出错',
                                    msg: result.message,
                                    icon: Ext.MessageBox.ERROR,
                                    buttons: Ext.Msg.OK

                                });
                            }
                        }
                    }
                });
                store.load({
                    scope: this,
                    callback: function(records, operation,success){
                        if(records.length === 0){
                            Ext.getCmp('fullname').focus();
                            Ext.Msg.alert('提示','没有可选级别，请先在数据库加入级别！');
                        }
                    }
                });

    },

    onAccessrolecomboFocus: function(component, event, eOpts) {
        var store = Ext.data.StoreManager.lookup('AccessRoleListStore');
        store.setProxy({
                    type: 'ajax',
                    url: 'getAllAccessRole.action',
                    reader: {
                        type: 'json',
                        rootProperty: 'results'
                    },
                    listeners: {
                        exception:{
                            fn:function(server,response,operation,options){
                                var result = Ext.JSON.decode(response.responseText);
                                Ext.getCmp('fullname').focus();
                                Ext.MessageBox.show({
                                    title: '服务器出错',
                                    msg: result.message,
                                    icon: Ext.MessageBox.ERROR,
                                    buttons: Ext.Msg.OK

                                });
                            }
                        }
                    }
                });
                store.load({
                    scope: this,
                    callback: function(records, operation,success){
                        if(records.length === 0){
                            Ext.getCmp('fullname').focus();
                            Ext.Msg.alert('提示','没有可选权限，请先在数据库加入权限！');
                        }
                    }
                });
    },

    onAccessrolecomboChange: function(field, newValue, oldValue, eOpts) {

    },

    onManagercomboFocus: function(component, event, eOpts) {
        var depId = Ext.getCmp('departmentcombo').getValue();
        var store = Ext.data.StoreManager.lookup('ManagerListStore');
        store.setProxy({
                    type: 'ajax',
                    url: 'getAllDepManagers.action',
            extraParams:{
                departmentId: depId
            },
                    reader: {
                        type: 'json',
                        rootProperty: 'results'
                    },
                    listeners: {
                        exception:{
                            fn:function(server,response,operation,options){
                                var result = Ext.JSON.decode(response.responseText);
                                Ext.getCmp('fullname').focus();
                                Ext.MessageBox.show({
                                    title: '服务器出错',
                                    msg: result.message,
                                    icon: Ext.MessageBox.ERROR,
                                    buttons: Ext.Msg.OK

                                });
                            }
                        }
                    }
                });
                store.load({
                    scope: this,
                    callback: function(records, operation,success){
                        if(records.length === 0){
                           Ext.getCmp('fullname').focus();
                           Ext.Msg.alert('提示','没有可选经理，请先在数据库加入该部门经理！');

                        }
                    }
                });
    },

    onManagercomboChange: function(field, newValue, oldValue, eOpts) {

    },

    onAddusersubmitClick: function(button, e, eOpts) {

    },

    onAdduserresetClick: function(button, e, eOpts) {

    },

    validateUserName: function(value) {

        Ext.Ajax.setTimeout(20000);
        Ext.Ajax.request({
                                    url:'admin/userIdValidate.action',
                                    method: 'GET',
                                    async: false,
                                    params :{
                                        userName : value
                                    },
                                    success: function(response){
                                        var respText = Ext.JSON.decode(response.responseText);
                                        if(respText.success){
                                            Ext.getCmp('password').focus();
                                            Ext.Msg.alert('提示','用户名可用');
                                        }

                                        else{
                                            Ext.getCmp('username').focus();
                                            Ext.Msg.alert('提示',value + '已经存在');
                                        }


                                    },
                                    failure: function(form,action){

                                        Ext.getCmp('username').focus();
                                        Ext.Msg.alert('提示','验证用户名出错');

                                    }

                                });

    }

});