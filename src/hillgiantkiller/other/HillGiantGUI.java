package hillgiantkiller.other;

import hillgiantkiller.Hill_Giant_Killer;
import hillgiantkiller.enums.Food;
import hillgiantkiller.enums.Loot;
import hillgiantkiller.enums.Rune;
import hillgiantkiller.enums.Skill;
import hillgiantkiller.nodes.Banking;
import hillgiantkiller.nodes.Looting;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;


public class HillGiantGUI extends JFrame {

    private JPanel contentPane;
    private JTextField customFood;
    private JTextField foodAmount;
    private JTextField txtLootAfter;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField txtLootPrice;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HillGiantGUI frame = new HillGiantGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public HillGiantGUI() {
        setTitle("Hill Giant Fighter");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 396, 402);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane);

        JPanel mainTab = new JPanel();
        tabbedPane.addTab("Main", null, mainTab, null);
        tabbedPane.setEnabledAt(0, true);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Abilities", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Food", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        final JRadioButton btnEatFood = new JRadioButton("Eat Food");

        final JComboBox<Food> foodList = new JComboBox<>(new DefaultComboBoxModel<>(Food.values()));
        foodList.setEnabled(false);

        customFood = new JTextField();
        customFood.setEnabled(false);
        customFood.setText("Custom ID");
        customFood.setColumns(10);

        JLabel lblWithdraw = new JLabel("Withdraw");

        foodAmount = new JTextField();
        foodAmount.setText("4");
        foodAmount.setEnabled(false);
        foodAmount.setColumns(10);

        JLabel lblFood = new JLabel("food");
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnEatFood)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addGap(21)
                                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(customFood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(foodList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addComponent(lblWithdraw)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(foodAmount, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lblFood)))
                                .addContainerGap(44, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnEatFood)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(foodList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(customFood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblWithdraw)
                                        .addComponent(foodAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblFood))
                                .addGap(19))
        );
        panel_1.setLayout(gl_panel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Other", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JLabel lblTrainingWhatSkill = new JLabel("Training what skill: ");

        final JComboBox<Skill> skillTraining = new JComboBox<>(new DefaultComboBoxModel<>(Skill.values()));

        final JRadioButton btnResourceDung = new JRadioButton("Use resource dungeon");
        GroupLayout gl_mainTab = new GroupLayout(mainTab);
        gl_mainTab.setHorizontalGroup(
                gl_mainTab.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_mainTab.createSequentialGroup()
                                .addGroup(gl_mainTab.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(Alignment.LEADING, gl_mainTab.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
                                        .addGroup(gl_mainTab.createSequentialGroup()
                                                .addGap(10)
                                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
                                .addGap(14))
        );
        gl_mainTab.setVerticalGroup(
                gl_mainTab.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_mainTab.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_mainTab.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 153, Short.MAX_VALUE)
                                .addContainerGap())
        );

        JSeparator separator = new JSeparator();

        JSeparator separator_1 = new JSeparator();

        final JSlider lootRadiusSlider = new JSlider();
        lootRadiusSlider.setEnabled(false);
        lootRadiusSlider.setMinorTickSpacing(1);
        lootRadiusSlider.setPaintLabels(true);
        lootRadiusSlider.setSnapToTicks(true);
        lootRadiusSlider.setPaintTicks(true);
        lootRadiusSlider.setValue(2);
        lootRadiusSlider.setMaximum(10);
        lootRadiusSlider.setMinimum(1);
        lootRadiusSlider.setMajorTickSpacing(4);

        JLabel lblLootRadius = new JLabel("Loot Radius");
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
                gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel_2.createSequentialGroup()
                                                .addGap(6)
                                                .addComponent(lblTrainingWhatSkill)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(skillTraining, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(separator, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnResourceDung)
                                        .addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
                                                .addComponent(lblLootRadius)
                                                .addGap(18)
                                                .addComponent(lootRadiusSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        gl_panel_2.setVerticalGroup(
                gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(skillTraining, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTrainingWhatSkill))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnResourceDung)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel_2.createSequentialGroup()
                                                .addGap(18)
                                                .addComponent(lblLootRadius))
                                        .addGroup(gl_panel_2.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lootRadiusSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(19))
        );
        panel_2.setLayout(gl_panel_2);

        final JRadioButton btnUseAbilities = new JRadioButton("Use abilities");

        final JRadioButton btnUseMomentum = new JRadioButton("Use Momentum");
        buttonGroup.add(btnUseMomentum);
        btnUseMomentum.setEnabled(false);

        final JRadioButton btnUseRejuvenate = new JRadioButton("Use Rejuvenate");
        buttonGroup.add(btnUseRejuvenate);
        btnUseRejuvenate.setEnabled(false);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(21)
                                                .addComponent(btnUseMomentum))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(21)
                                                .addComponent(btnUseRejuvenate))
                                        .addComponent(btnUseAbilities))
                                .addContainerGap(65, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnUseAbilities)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnUseMomentum)
                                .addGap(3)
                                .addComponent(btnUseRejuvenate)
                                .addContainerGap(54, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        mainTab.setLayout(gl_mainTab);

        JPanel lootingTab = new JPanel();
        tabbedPane.addTab("Looting", null, lootingTab, null);

        JScrollPane panel_3 = new JScrollPane();
        panel_3.setBorder(new TitledBorder(null, "Loot Choices", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Loot Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new TitledBorder(null, "Custom Loot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout gl_lootingTab = new GroupLayout(lootingTab);
        gl_lootingTab.setHorizontalGroup(
                gl_lootingTab.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_lootingTab.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_lootingTab.createParallelGroup(Alignment.LEADING)
                                        .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 196, Short.MAX_VALUE)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        gl_lootingTab.setVerticalGroup(
                gl_lootingTab.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_lootingTab.createSequentialGroup()
                                .addGroup(gl_lootingTab.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_lootingTab.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                                        .addGroup(gl_lootingTab.createSequentialGroup()
                                                .addGap(18)
                                                .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        final JEditorPane txtLootIds = new JEditorPane();
        txtLootIds.setEditable(false);
        txtLootIds.setText("Enter Loot ID's here");
        scrollPane.setViewportView(txtLootIds);

        final JRadioButton btnShouldBurry = new JRadioButton("Burry Bones");
        btnShouldBurry.setEnabled(false);

        final JRadioButton btnEatFoodForLoot = new JRadioButton("Eat food for space");
        btnEatFoodForLoot.setEnabled(false);

        final JRadioButton btnShouldLoot = new JRadioButton("Should loot");

        JLabel lblNewLabel = new JLabel("Loot after");

        txtLootAfter = new JTextField();
        txtLootAfter.setEnabled(false);
        txtLootAfter.setText("1");
        txtLootAfter.setColumns(10);

        JLabel lblKill = new JLabel("kill(s)");

        final JRadioButton btnLootByPrice = new JRadioButton("Loot by price");
        btnLootByPrice.setEnabled(false);

        JLabel lblAnythingOver = new JLabel("Anything over");

        txtLootPrice = new JTextField();
        txtLootPrice.setText("1000");
        txtLootPrice.setEnabled(false);
        txtLootPrice.setColumns(10);
        GroupLayout gl_panel_4 = new GroupLayout(panel_4);
        gl_panel_4.setHorizontalGroup(
                gl_panel_4.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_4.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel_4.createSequentialGroup()
                                                .addGap(21)
                                                .addComponent(lblAnythingOver)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(txtLootPrice, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                                .addGap(39))
                                        .addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_panel_4.createSequentialGroup()
                                                        .addComponent(btnEatFoodForLoot)
                                                        .addContainerGap())
                                                .addComponent(btnShouldLoot)
                                                .addComponent(btnShouldBurry)
                                                .addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
                                                        .addComponent(btnLootByPrice)
                                                        .addGap(49)))
                                        .addGroup(gl_panel_4.createSequentialGroup()
                                                .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(txtLootAfter, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lblKill)
                                                .addGap(82))))
        );
        gl_panel_4.setVerticalGroup(
                gl_panel_4.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_4.createSequentialGroup()
                                .addComponent(btnShouldLoot)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnShouldBurry)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnEatFoodForLoot)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnLootByPrice)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblAnythingOver)
                                        .addComponent(txtLootPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(txtLootAfter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblKill))
                                .addGap(29))
        );
        panel_4.setLayout(gl_panel_4);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBorder(new TitledBorder(null, "kl;kl;", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.add(scrollBar);

        final JList<Loot> lootList = new JList<>();
        lootList.setListData(Loot.values());
        panel_3.setViewportView(lootList);
        lootList.setEnabled(false);
        panel_3.setViewportView(lootList);
        lootList.setAutoscrolls(false);
        lootingTab.setLayout(gl_lootingTab);

        JPanel mage_rangeTab = new JPanel();
        tabbedPane.addTab("Mage/Range", null, mage_rangeTab, null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBorder(new TitledBorder(null, "Runes", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        final JRadioButton btnKeepRunes = new JRadioButton("Keep runes in inventory ");
        btnKeepRunes.setEnabled(false);

        final JRadioButton btnMage = new JRadioButton("Mage?");

        final JRadioButton btnRange = new JRadioButton("Range?");

        final JRadioButton btnEquipArrows = new JRadioButton("Equip Arrows");
        btnEquipArrows.setEnabled(false);
        btnEquipArrows.setToolTipText("Whenever arrows are looted that match the ID of the arrow you have equiped, will re equip looted arrows");

        JSeparator separator_2 = new JSeparator();
        separator_2.setOrientation(SwingConstants.VERTICAL);

        JSeparator separator_3 = new JSeparator();

        JCheckBox chkSafeSpot = new JCheckBox("Safe Spot");
        chkSafeSpot.setEnabled(false);
        GroupLayout gl_mage_rangeTab = new GroupLayout(mage_rangeTab);
        gl_mage_rangeTab.setHorizontalGroup(
                gl_mage_rangeTab.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_mage_rangeTab.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_mage_rangeTab.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnMage)
                                        .addComponent(btnKeepRunes)
                                        .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addGroup(gl_mage_rangeTab.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnRange)
                                        .addComponent(btnEquipArrows))
                                .addContainerGap(93, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_mage_rangeTab.createSequentialGroup()
                                .addContainerGap(15, Short.MAX_VALUE)
                                .addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_mage_rangeTab.createSequentialGroup()
                                .addGap(126)
                                .addComponent(chkSafeSpot)
                                .addContainerGap(150, Short.MAX_VALUE))
        );
        gl_mage_rangeTab.setVerticalGroup(
                gl_mage_rangeTab.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_mage_rangeTab.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_mage_rangeTab.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_mage_rangeTab.createSequentialGroup()
                                                .addGroup(gl_mage_rangeTab.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btnMage)
                                                        .addComponent(btnRange))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(gl_mage_rangeTab.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_mage_rangeTab.createSequentialGroup()
                                                                .addComponent(btnKeepRunes)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(btnEquipArrows)))
                                        .addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(chkSafeSpot)
                                .addContainerGap(92, Short.MAX_VALUE))
        );

        final JList<Rune> lstRunes = new JList<>();
        lstRunes.setListData(Rune.values());
        lstRunes.setEnabled(false);
        scrollPane_1.setViewportView(lstRunes);
        mage_rangeTab.setLayout(gl_mage_rangeTab);

        JPanel startPanel = new JPanel();
        tabbedPane.addTab("Start", null, startPanel, null);

        JButton btnStart = new JButton("START");
        btnStart.setFont(new Font("Tahoma", Font.PLAIN, 33));
        GroupLayout gl_startPanel = new GroupLayout(startPanel);
        gl_startPanel.setHorizontalGroup(
                gl_startPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_startPanel.createSequentialGroup()
                                .addGap(86)
                                .addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(87, Short.MAX_VALUE))
        );
        gl_startPanel.setVerticalGroup(
                gl_startPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_startPanel.createSequentialGroup()
                                .addGap(108)
                                .addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(108, Short.MAX_VALUE))
        );
        startPanel.setLayout(gl_startPanel);


        btnUseAbilities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!btnUseMomentum.isEnabled() && !btnUseRejuvenate.isEnabled()){
                    btnUseMomentum.setEnabled(true);
                    btnUseRejuvenate.setEnabled(true);
                }else{
                    buttonGroup.clearSelection();
                    btnUseMomentum.setEnabled(false);
                    btnUseRejuvenate.setEnabled(false);
                }
            }
        });

        btnEatFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!foodList.isEnabled() && !foodAmount.isEnabled()) {
                    foodList.setEnabled(true);
                    foodAmount.setEnabled(true);
                } else {
                    foodList.setEnabled(false);
                    foodList.setSelectedIndex(0);
                    foodAmount.setEnabled(false);
                    foodAmount.setText("");
                    customFood.setEnabled(false);
                    customFood.setText("Custom ID");
                }
            }
        });

        foodList.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String x = String.valueOf(foodList.getSelectedItem());
                if (x.equalsIgnoreCase("custom id")) {
                    customFood.setEnabled(true);
                    customFood.setText("");
                } else {
                    customFood.setEnabled(false);
                    customFood.setText("Custom ID");
                }
            }
        });

        btnShouldLoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnShouldLoot.isSelected()){
                    btnShouldBurry.setEnabled(true);
                    if(btnEatFood.isSelected())btnEatFoodForLoot.setEnabled(true);
                    btnLootByPrice.setEnabled(true);
                    txtLootAfter.setEnabled(true);
                    lootList.setEnabled(true);
                    lootRadiusSlider.setEnabled(true);
                    txtLootIds.setEditable(true);
                    txtLootIds.setText("");

                }else{
                    btnShouldBurry.setEnabled(false);
                    btnShouldBurry.setSelected(false);

                    btnEatFoodForLoot.setEnabled(false);
                    btnEatFoodForLoot.setSelected(false);

                    btnLootByPrice.setEnabled(false);
                    btnLootByPrice.setSelected(false);

                    txtLootPrice.setEnabled(false);
                    txtLootPrice.setText("1000");

                    txtLootAfter.setEnabled(false);
                    txtLootAfter.setText("1");
                    lootList.setEnabled(false);
                    lootList.clearSelection();
                    lootRadiusSlider.setEnabled(false);
                    txtLootIds.setEditable(false);
                    txtLootIds.setText("");


                }
            }
        });

        btnLootByPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnShouldLoot.isSelected() && btnLootByPrice.isSelected()){
                    txtLootPrice.setEnabled(true);
                }else{
                    txtLootPrice.setEnabled(false);
                    txtLootPrice.setText("1000");
                }
            }
        });

        btnMage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnMage.isSelected()){
                    btnKeepRunes.setSelected(true);
                    lstRunes.setEnabled(true);
                } else{
                    btnKeepRunes.setSelected(false);

                    lstRunes.setEnabled(false);
                }
            }
        });

        btnRange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnRange.isSelected()){
                    btnEquipArrows.setSelected(true);
                } else{
                    btnEquipArrows.setSelected(false);
                }
            }
        });



        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(btnResourceDung.isSelected()){
                   Variables.useResourceDungeon = true;
                }

                Variables.lootRadius = lootRadiusSlider.getValue();

                if(btnUseAbilities.isSelected()){
                    Variables.useAbilities = true;
                    if(btnUseMomentum.isSelected()) Variables.useMomentum = true;
                    if(btnUseRejuvenate.isSelected()) Variables.useRejuvenate = true;

                }
                if(btnEatFood.isSelected()){
                    Variables.eatFood = true;
                    Food temp = (Food) foodList.getSelectedItem();
                    if(temp.getId() == 1){
                        Variables.foodId = Integer.parseInt(customFood.getText());
                    }else{
                        Variables.foodId = temp.getId();
                    }
                    Variables.withdrawFoodAmount = Integer.parseInt(foodAmount.getText());

                }

                Skill skill = (Skill) skillTraining.getSelectedItem();
                Paint.skill = skill.getSkillID();

                if(btnShouldLoot.isSelected()){
                    Variables.shouldLoot = true;
                    int tempLootAfter = Integer.parseInt(txtLootAfter.getText());
                    if(tempLootAfter == 0){
                        Variables.lootAfter = 1;
                        Variables.shouldWaitForLoot = true;
                    }
                    Variables.lootAfter = Integer.parseInt(txtLootAfter.getText());
                    if(btnShouldBurry.isSelected()){
                        Variables.burryBones = true;
                    }
                    if(btnEatFoodForLoot.isSelected()){
                        Variables.eatFoodForSpace = true;
                    }
                    if(btnLootByPrice.isSelected()){
                        Variables.lootByPrice = true;
                        Variables.minPriceToLoot = Integer.parseInt(txtLootPrice.getText());
                    }
                    List<Loot> lootList1 = lootList.getSelectedValuesList();
                    if(lootList1 != null){
                       for(Loot x: lootList1){
                           for(int i: x.getId()){
                               Looting.lootIds.add(i);
                           }
                       }
                    }

                    String[] split = txtLootIds.getText().split("\\r?\\n");
                    if(!split[0].equals("")){
                        for(String s: split){
                            Looting.lootIds.add(Integer.parseInt(s));
                        }
                    }
                }

                if(btnMage.isSelected()){
                    List<Rune> runes = lstRunes.getSelectedValuesList();
                    for(Rune x: runes){
                        Banking.runesToKeep.add(x.getId());
                    }
                }

                Hill_Giant_Killer.guiWait = false;
                destroy();

            }
        });



    }

    private void destroy(){
        this.dispose();
    }
}
