package hillgiantkiller.other;

import hillgiantkiller.Hill_Giant_Killer;
import hillgiantkiller.enums.FoodEnum;
import hillgiantkiller.enums.LootEnum;
import hillgiantkiller.nodes.Loot;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class HillGiantGUI extends JFrame {

    private final JTextField customFood;
    private final JTextField foodAmount;
    private final JTextField txtLootAfter;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final JTextField txtLootPrice;

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
        setTitle("Kirinsoul's Hill Giant Fighter");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 396, 326);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane);

        JPanel combatTab = new JPanel();
        tabbedPane.addTab("Combat", null, combatTab, null);
        tabbedPane.setEnabledAt(0, true);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Abilities", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Food", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        final JRadioButton btnEatFood = new JRadioButton("Eat Food");

        final JComboBox<FoodEnum> foodList = new JComboBox<>();
        foodList.setEnabled(false);
        DefaultComboBoxModel<FoodEnum> fromEnum = new DefaultComboBoxModel<>(FoodEnum.values());
        foodList.setModel(fromEnum);

        customFood = new JTextField();
        customFood.setEnabled(false);
        customFood.setText("Custom ID");
        customFood.setColumns(10);

        JLabel lblWithdraw = new JLabel("Withdraw");

        foodAmount = new JTextField();
        foodAmount.setText("15");
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
        panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Skills", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JLabel lblTrainingWhatSkill = new JLabel("Training what skill: ");

        final JComboBox<String> skillTraining = new JComboBox<>();
        skillTraining.setModel(new DefaultComboBoxModel<>(new String[] {"Attack", "Strength", "Defense", "[A,S,D]", "Ranged", "Magic"}));
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
                gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTrainingWhatSkill)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(skillTraining, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_2.setVerticalGroup(
                gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblTrainingWhatSkill)
                                        .addComponent(skillTraining, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        panel_2.setLayout(gl_panel_2);
        GroupLayout gl_combatTab = new GroupLayout(combatTab);
        gl_combatTab.setHorizontalGroup(
                gl_combatTab.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_combatTab.createSequentialGroup()
                                .addGap(10)
                                .addGroup(gl_combatTab.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_combatTab.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                                        .addGroup(gl_combatTab.createSequentialGroup()
                                                .addGap(67)
                                                .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(57)))
                                .addGap(14))
        );
        gl_combatTab.setVerticalGroup(
                gl_combatTab.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_combatTab.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_combatTab.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

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
        combatTab.setLayout(gl_combatTab);

        JPanel lootingTab = new JPanel();
        tabbedPane.addTab("Looting", null, lootingTab, null);

        JScrollPane panel_3 = new JScrollPane();
        panel_3.setBorder(new TitledBorder(null, "Loot Choices", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Loot Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout gl_lootingTab = new GroupLayout(lootingTab);
        gl_lootingTab.setHorizontalGroup(
                gl_lootingTab.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_lootingTab.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 188, Short.MAX_VALUE)
                                .addContainerGap())
        );
        gl_lootingTab.setVerticalGroup(
                gl_lootingTab.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_lootingTab.createSequentialGroup()
                                .addGroup(gl_lootingTab.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_lootingTab.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_lootingTab.createSequentialGroup()
                                                .addGap(18)
                                                .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(16, Short.MAX_VALUE))
        );

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
        panel_3.add(scrollBar);

        final JList<LootEnum> lootList = new JList<>();
        panel_3.setViewportView(lootList);
        lootList.setEnabled(false);
        panel_3.setViewportView(lootList);
        lootList.setAutoscrolls(false);
        lootList.setModel(new DefaultListModel<LootEnum>() {
            final LootEnum[] values = LootEnum.values();

            public int getSize() {
                return values.length;
            }

            public LootEnum getElementAt(int index) {
                return values[index];
            }
        });
        lootingTab.setLayout(gl_lootingTab);

        JPanel startPanel = new JPanel();
        tabbedPane.addTab("Start", null, startPanel, null);

        JButton btnStart = new JButton("START");
        btnStart.setFont(new Font("Tahoma", Font.PLAIN, 33));
        GroupLayout gl_startPanel = new GroupLayout(startPanel);
        gl_startPanel.setHorizontalGroup(
                gl_startPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_startPanel.createSequentialGroup()
                                .addGap(93)
                                .addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(94, Short.MAX_VALUE))
        );
        gl_startPanel.setVerticalGroup(
                gl_startPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_startPanel.createSequentialGroup()
                                .addGap(66)
                                .addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(83, Short.MAX_VALUE))
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
                if(x.equalsIgnoreCase("custom id")){
                    customFood.setEnabled(true);
                    customFood.setText("");
                }else{
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

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnUseAbilities.isSelected()){
                    Variables.useAbilities = true;
                    if(btnUseMomentum.isSelected()) Variables.useMomentum = true;
                    if(btnUseRejuvenate.isSelected()) Variables.useRejuvenate = true;

                }
                if(btnEatFood.isSelected()){
                    Variables.eatFood = true;
                    FoodEnum temp = (FoodEnum) foodList.getSelectedItem();
                    if(temp.getId() == 1){
                        Variables.foodId = Integer.parseInt(customFood.getText());
                    }else{
                        Variables.foodId = temp.getId();
                    }
                    Variables.withdrawFoodAmount = Integer.parseInt(foodAmount.getText());

                }
                Variables.skillTraining = skillTraining.getSelectedIndex();
                if(btnShouldLoot.isSelected()){
                    Variables.shouldLoot = true;
                    System.out.println(Variables.shouldLoot);
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
                        System.out.println(Variables.lootByPrice);
                        Variables.minPriceToLoot = Integer.parseInt(txtLootPrice.getText());
                    }
                    //LootEnum[] temp = (LootEnum[]) lootList.getSelectedValuesList().toArray();
                    Object[] temp = lootList.getSelectedValuesList().toArray();
                    System.out.println(temp);
                    if(temp != null){
                        for(Object  x: temp){
                            LootEnum casted = (LootEnum) x;
                            for(int i: casted.getId()){
                                if(i != 1){
                                    Loot.lootIds.add(i);
                                }
                            }

                        }
                    }



                }

                Hill_Giant_Killer.guiWait = false;
            }
        });




    }



}
