/*
 * File: app/store/ClothesCategoryTreeStore.js
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

Ext.define('Xixixi.store.ClothesCategoryTreeStore', {
    extend: 'Ext.data.TreeStore',

    requires: [
        'Xixixi.model.ClothesCategoryPrice'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'ClothesCategoryTreeStore',
            autoLoad: false,
            model: 'Xixixi.model.ClothesCategoryPrice',
            root: {
                text: '.',
                children: [
                    {
                        category: '上装',
                        amount: 0,
                        unitPrice: 0,
                        expanded: true,
                        children: [
                            {
                                category: '马甲',
                                amount: 0,
                                unitPrice: 10,
                                expanded: false
                            },
                            {
                                category: 'T恤/衬衫',
                                amount: 0,
                                unitPrice: 6,
                                expanded: false
                            }
                        ]
                    },
                    {
                        category: '下装',
                        amount: 0,
                        unitPrice: 0,
                        expanded: true,
                        children: [
                            {
                                category: '马甲',
                                amount: 0,
                                unitPrice: 10,
                                expanded: false
                            },
                            {
                                category: 'T恤/衬衫',
                                amount: 0,
                                unitPrice: 6,
                                expanded: false
                            }
                        ]
                    },
                    {
                        category: '上装',
                        amount: 0,
                        unitPrice: 0,
                        expanded: true,
                        children: [
                            {
                                category: '马甲',
                                amount: 0,
                                unitPrice: 10,
                                expanded: false
                            },
                            {
                                category: 'T恤/衬衫',
                                amount: 0,
                                unitPrice: 6,
                                expanded: false
                            }
                        ]
                    },
                    {
                        category: '下装',
                        amount: 0,
                        unitPrice: 0,
                        expanded: true,
                        children: [
                            {
                                category: '马甲',
                                amount: 0,
                                unitPrice: 10,
                                expanded: false
                            },
                            {
                                category: 'T恤/衬衫',
                                amount: 0,
                                unitPrice: 6,
                                expanded: false
                            }
                        ]
                    },
                    {
                        category: '上装',
                        amount: 0,
                        unitPrice: 0,
                        expanded: true,
                        children: [
                            {
                                category: '马甲',
                                amount: 0,
                                unitPrice: 10,
                                expanded: false
                            },
                            {
                                category: 'T恤/衬衫',
                                amount: 0,
                                unitPrice: 7,
                                expanded: false
                            }
                        ]
                    },
                    {
                        category: '下装',
                        amount: 0,
                        unitPrice: 0,
                        expanded: true,
                        children: [
                            {
                                category: '马甲',
                                amount: 0,
                                unitPrice: 10,
                                expanded: false
                            },
                            {
                                category: 'T恤/衬衫',
                                amount: 0,
                                unitPrice: 5,
                                expanded: false
                            }
                        ]
                    },
                    {
                        category: '上装',
                        amount: 0,
                        unitPrice: 0,
                        expanded: true,
                        children: [
                            {
                                category: '马甲',
                                amount: 0,
                                unitPrice: 10,
                                expanded: false
                            },
                            {
                                category: 'T恤/衬衫',
                                amount: 0,
                                unitPrice: 6,
                                expanded: false
                            }
                        ]
                    },
                    {
                        category: '下装',
                        amount: 0,
                        unitPrice: 0,
                        expanded: true,
                        children: [
                            {
                                category: '马甲',
                                amount: 0,
                                unitPrice: 10,
                                expanded: false
                            },
                            {
                                category: 'T恤/衬衫',
                                amount: 0,
                                unitPrice: 6,
                                expanded: false
                            }
                        ]
                    }
                ]
            }
        }, cfg)]);
    }
});