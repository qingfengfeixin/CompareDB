/*
 * Copyright 2014 Hyberbin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Email:hyberbin@qq.com
 */
package com.hyberbin.frame;

import com.hyberbin.bean.DbLinkBean;
import com.hyberbin.db.SqliteDao;
import com.hyberbin.model.LalelListModel;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.jplus.hyb.log.Logger;
import org.jplus.hyb.log.LoggerManager;
import org.jplus.util.ObjectHelper;

/**
 *
 * @author Hyberbin
 */
public class DbLinkManager extends javax.swing.JFrame {

    protected static Logger log = LoggerManager.getLogger(DbLinkManager.class);
    private LalelListModel[] models;

    /** Creates new form UpdateLangXMLFrame */
    public DbLinkManager() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        setLocation((tk.getScreenSize().width - getSize().width) / 2,
                (tk.getScreenSize().height - getSize().height) / 2);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("链接管理");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
            }
        });
        DefaultTableModel model = (DefaultTableModel) dblink.getModel();
        List<DbLinkBean> dblinkInfo = SqliteDao.getDblinkInfo();
        if (ObjectHelper.isNotEmpty(dblinkInfo)) {
            for (DbLinkBean bean : dblinkInfo) {
                model.addRow(new Object[]{bean.getLable(), bean.getUrl(),bean.getUser(), bean.getPassword()});
            }
        }
    }

    public DbLinkManager(LalelListModel[] models) {
        this();
        this.models = models;
    }

    /** This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dblink = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dblink.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "名称", "url", "user", "password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dblink);

        jButton2.setText("保存表格");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("添加行");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addGap(13, 13, 13))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) dblink.getModel();
        model.addRow(new Object[]{"", "", "", ""});
    }//GEN-LAST:event_jButton3ActionPerformed
    private List<DbLinkBean> getDbLinkBeans() {
        DefaultTableModel model = (DefaultTableModel) dblink.getModel();
        List<DbLinkBean> dbLinkBeans = new ArrayList<DbLinkBean>(0);
        int row = model.getRowCount();
        log.debug("to do row size:" + row);
        for (int i = 0; i < row; i++) {
            DbLinkBean bean = new DbLinkBean();
            bean.setLable(model.getValueAt(i, 0) + "");
            bean.setUrl(model.getValueAt(i, 1) + "");
            bean.setUser(model.getValueAt(i, 2) + "");
            bean.setPassword(model.getValueAt(i, 3) + "");
            if (!ObjectHelper.isNullOrEmptyString(bean.getUrl())) {
                dbLinkBeans.add(bean);
            }
        }
        return dbLinkBeans;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SqliteDao.clearDblink();
        log.debug("cleared");
        List<DbLinkBean> dbLinkBeans = getDbLinkBeans();
        for (DbLinkBean bean : dbLinkBeans) {
            SqliteDao.insertDbLink(bean);
            log.debug("insert " + bean.getUrl());
        }
        if (models != null) {
            for (LalelListModel model : models) {
                model.removeAllElements();
                model.getList();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dblink;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
