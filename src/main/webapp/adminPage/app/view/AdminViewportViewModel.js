Ext.define('Xixixi.view.AdminViewportViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.adminviewport',

    requires: [
        'Ext.data.Store',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json',
        'Ext.data.writer.Json'
    ],

    stores: {
        userListViewStore: {
            autoLoad: true,
            model: 'Xixixi.model.UserListModel',
            proxy: {
                type: 'ajax',
                url: 'admin/userLoad.action',
                reader: {
                    type: 'json',
                    rootProperty: 'results'
                },
                writer: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        },
        departmentListViewStore: {
            autoLoad: false,
            model: 'Xixixi.model.DepartmentListModel',
            proxy: {
                type: 'ajax',
                url: 'admin/departmentLoad.action',
                reader: {
                    type: 'json',
                    rootProperty: 'results'
                },
                writer: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        }
    }

});