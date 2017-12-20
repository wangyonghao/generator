/*
 * Copyright 2014 ptma@163.com
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
 */
package org.joy.generator.ui;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joy.dbutils.model.Table;
import org.joy.generator.config.Configuration;
import org.joy.generator.config.model.TemplateElement;
import org.joy.generator.engine.EngineFactory;
import org.joy.generator.engine.TemplateEngine;
import org.joy.generator.ui.component.CheckBoxList;
import org.joy.util.StringUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GenerationDialog extends JDialog {
    private final JPanel      contentPanel     = new JPanel();
    private JTextField        textTargetProject;
    private JTextField        textBasePackage;
    private JTextField textAuthor;
    private CheckBoxList      templatesList;

    private Configuration     configuration;
    private Table             tableModel;

    private JTextField        textModuleName;
    private JTextField        textTableName;
    private JTextField        textTableAlias;

    /**
     * Create the dialog.
     */
    public GenerationDialog(Configuration configuration, Table tableModel){
        setModal(true);
        setTitle("生成代码");
        setResizable(false);
        setBounds(100, 100, 479, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        int x1 = 10;
        int y1 = 15;
        int x2 = 92;
        int y2 = 12;

        int width1 = 72;
        int height1 = 15;
        int width2 = 350;
        int height2 = 25;

        JLabel lblTargetProject = new JLabel("代码保存路径");
        lblTargetProject.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTargetProject.setBounds(x1, y1, width1, height1);
        contentPanel.add(lblTargetProject);

        textTargetProject = new JTextField();
        textTargetProject.setBounds(x2, y2, width2, height2);
        contentPanel.add(textTargetProject);
        textTargetProject.setColumns(10);

        y1 += 35;
        y2 += 35;

        JLabel lblBasePackage = new JLabel("基准包");
        lblBasePackage.setHorizontalAlignment(SwingConstants.TRAILING);
        lblBasePackage.setBounds(x1, y1, width1, height1);
        contentPanel.add(lblBasePackage);

        textBasePackage = new JTextField();
        textBasePackage.setBounds(x2, y2, width2, height2);
        contentPanel.add(textBasePackage);
        textBasePackage.setColumns(10);

        y1 += 35;
        y2 += 35;

        JLabel lblModuleName = new JLabel("模块名");
        lblModuleName.setHorizontalAlignment(SwingConstants.TRAILING);
        lblModuleName.setBounds(x1, y1, width1, height1);
        contentPanel.add(lblModuleName);

        textModuleName = new JTextField();
        textModuleName.setBounds(x2, y2, width2, height2);
        contentPanel.add(textModuleName);
        textModuleName.setColumns(10);

        y1 += 35;
        y2 += 35;

        JLabel lblAuthor = new JLabel("作者");
        lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAuthor.setBounds(x1, y1, width1, height1);
        contentPanel.add(lblAuthor);

        textAuthor = new JTextField();
        textAuthor.setBounds(x2, y2, width2, height2);
        contentPanel.add(textAuthor);
        textAuthor.setColumns(10);

        y1 += 35;
        y2 += 35;

        JLabel lblTemplates = new JLabel("代码模板");
        lblTemplates.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTemplates.setBounds(x1, y1, width1, height1);
        contentPanel.add(lblTemplates);

        JPanel panel = new JPanel();
        panel.setBounds(x2-3, y2-3, width2+6, 244);
        contentPanel.add(panel);
        panel.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane);
        templatesList = new CheckBoxList();
        templatesList.setBounds(0, 0, 1, 1);
        scrollPane.setViewportView(templatesList);

        y1 += 244;
        y2 += 244;

        JLabel lblTableName = new JLabel("表名");
        lblTableName.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTableName.setBounds(x1, y1, width1, height1);
        contentPanel.add(lblTableName);

        textTableName = new JTextField();
        textTableName.setEditable(false);
        textTableName.setBounds(x2, y2, width2, height2);
        textTableName.setColumns(10);
        textTableName.setEnabled(false);
        contentPanel.add(textTableName);

        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton btnOK = new JButton("生成");
        buttonPane.add(btnOK);
        btnOK.setMnemonic(KeyEvent.VK_ENTER);
        btnOK.setActionCommand("OK");
        btnOK.addActionListener(e -> okButtonClick());
        getRootPane().setDefaultButton(btnOK);

        JButton btnCancel = new JButton("取消");
        buttonPane.add(btnCancel);
        btnCancel.addActionListener(e -> doClose());
        btnCancel.setMnemonic(KeyEvent.VK_CANCEL);
        btnCancel.setActionCommand("Cancel");

        this.configuration = configuration;
        this.tableModel = tableModel;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        centerScreen();
        loadConfiguration();
    }

    public void centerScreen() {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
    }

    private void loadConfiguration() {
        textTargetProject.setText(configuration.getTargetProject());
        textBasePackage.setText(configuration.getBasePackage());
        textAuthor.setText(configuration.getAuthor());
        textModuleName.setText(tableModel.getModuleName());

        templatesList.setListData(configuration.getTemplates().toArray());

        templatesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        for (int i = 0; i < configuration.getTemplates().size(); i++) {
            TemplateElement templateElement = configuration.getTemplates().get(i);
            if (templateElement.isSelected()) {
                templatesList.addSelectionInterval(i, i);
            }
        }

        textTableName.setText(StringUtils.lowerCase(tableModel.getTableName()));
    }

    private void doClose() {
        setVisible(false);
        dispose();
    }

    private void okButtonClick() {
        List<Object> selectedValues = templatesList.getSelectedValuesList();
        if (selectedValues.size()==0) {
            JOptionPane.showMessageDialog(this, "请选择模板.", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String targetProject = textTargetProject.getText();
        if (StringUtil.isEmpty(targetProject)) {
            JOptionPane.showMessageDialog(this, "请输入代码保存路径.", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String basePackage = textBasePackage.getText();
        if (StringUtil.isEmpty(basePackage)) {
            JOptionPane.showMessageDialog(this, "请输入基准包.", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String moduleName = textModuleName.getText();
        if (StringUtil.isEmpty(moduleName)) {
            JOptionPane.showMessageDialog(this, "请输入模块名.", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String author = textAuthor.getText();

        configuration.setTargetProject(targetProject);
        configuration.setBasePackage(basePackage);
        configuration.setAuthor(author);
        tableModel.setModuleName(moduleName);
        processSelectedTemplates(selectedValues);
    }

    private void processSelectedTemplates(List<Object> selectedTemplateElements){

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("targetProject", configuration.getTargetProject());
        model.put("basePackage", configuration.getBasePackage());
        model.put("author", configuration.getAuthor());
        model.put("table", tableModel);

        for (TemplateElement templateElement : configuration.getTemplates()) {
            templateElement.setSelected(false);
        }

        int count = selectedTemplateElements.size();
        for (int i=0;i<count; i++) {
            try {
                TemplateElement templateElement = (TemplateElement) selectedTemplateElements.get(i);
                templateElement.setSelected(true);

                TemplateEngine templateEngine = EngineFactory.getEngine(templateElement.getEngine());
                if(templateEngine==null){
                    JOptionPane.showMessageDialog(this, "没有对应的模板引擎: "+templateElement.getEngine(), "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    templateEngine.generate(model,templateElement.getTemplateFile(),templateElement.getTargetPath()+ "/" + templateElement.getTargetFileName());
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.info(e.getMessage(), e);
                JOptionPane.showMessageDialog(this, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
        configuration.save();
        JOptionPane.showMessageDialog(this, "生成完毕.", "提示", JOptionPane.INFORMATION_MESSAGE);
    }
}
