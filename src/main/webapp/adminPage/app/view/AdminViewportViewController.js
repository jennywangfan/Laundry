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

    onMenu_userAfterRender: function(component, eOpts) {
        Ext.get('menu_user').on('click',function(){
            Ext.getCmp('centerContainer').getLayout().setActiveItem(0);

            var userListStore = this.getStore("userListViewStore");
            //    console.log(this);
            //   console.log(userListStore);
            userListStore.removeAll();
            userListStore.load();
        },this);
    },

    onMenu_departmentAfterRender: function(component, eOpts) {
        Ext.get('menu_department').on('click',function(){
            Ext.getCmp('centerContainer').getLayout().setActiveItem(1);
            var departmentListStore = this.getStore('departmentListViewStore');
            console.log(departmentListStore);
            departmentListStore.removeAll();
            departmentListStore.load();
        },this);
    },

    onMenu_adduserAfterRender: function(component, eOpts) {

    },

    onMenu_deleteuserAfterRender: function(component, eOpts) {

    },

    onMenu_refreshuserAfterRender: function(component, eOpts) {

        Ext.get('menu_refreshuser').on('click',function(){

            //Ext.Msg.alert('Success', respText.message);
            Ext.Msg.alert('refresh','start to refresh');
            var userListStore = this.getStore("userListViewStore");
            //    console.log(this);
            //   console.log(userListStore);
            userListStore.removeAll();
            userListStore.load();

        },this);
    },

    onGridpanelItemDblClick: function(dataview, record, item, index, e, eOpts) {

    },

    onMenu_adddepartmentAfterRender: function(component, eOpts) {

    },

    onMenu_deletedepartmentAfterRender: function(component, eOpts) {

    },

    onMenu_refreshdepartmentAfterRender: function(component, eOpts) {

    }

});
