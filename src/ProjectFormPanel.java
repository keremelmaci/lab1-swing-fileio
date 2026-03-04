import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ProjectFormPanel extends JPanel {

    private JTextField txtProjectName;
    private JTextField txtTeamLeader;
    private JTextField txtStartDate;

    private JComboBox<String> cmbTeamSize;
    private JComboBox<String> cmbProjectType;

    private JButton Buttonsave;
    private JButton Buttonclear;

public ProjectFormPanel() {

    setLayout(new GridLayout(6,2,10,10));
    txtProjectName = new JTextField();
    txtTeamLeader = new JTextField();
    txtStartDate = new JTextField();

    String[] teamsizes = {"Select", "1-3", "4-6", "7,10", "10+"};
    cmbTeamSize = new JComboBox<>(teamsizes);
    String[] projecttypes = {"Select", "Web", "Mobile", "Desktop", "API"};
    cmbProjectType = new JComboBox<>(projecttypes);

    Buttonsave = new JButton("Save");
    Buttonclear = new JButton("Clear");

    add(new JLabel("Project Name"));
    add(txtProjectName);

    add(new JLabel("Team Leader"));
    add(txtTeamLeader);

    add(new JLabel("Team Size"));
    add(cmbTeamSize);

    add(new JLabel("Project Type"));
    add(cmbProjectType);

    add(new JLabel("Start Date (DD/MM/YY)"));
    add(txtStartDate);

    add(Buttonsave);
    add(Buttonclear);

    Buttonsave.addActionListener(new SaveAction());
    Buttonclear.addActionListener(new ClearAction());


}

private class SaveAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        String projectName = txtProjectName.getText().trim();
        String teamLeader = txtTeamLeader.getText().trim();
        String startDate = txtStartDate.getText().trim();
        String teamSize = (String) cmbTeamSize.getSelectedItem();
        String projectType = (String) cmbProjectType.getSelectedItem();

        if (projectName.isEmpty() || teamLeader.isEmpty() || startDate.isEmpty() || teamSize.equals("Select") || projectType.equals("Select")) {

            JOptionPane.showMessageDialog(null,
                    "Please fill in all fields",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String recordTime = now.format(formatter);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true))) {

            writer.write("  Project Entry \n");
            writer.write("Project Name  : " + projectName + "\n");
            writer.write("Team Leader   : " + teamLeader + "\n");
            writer.write("Team Size     : " + teamSize + "\n");
            writer.write("Project Type  : " + projectType + "\n");
            writer.write("Start Date    : " + startDate + "\n");
            writer.write("Record Time   : " + recordTime + "\n");

            JOptionPane.showMessageDialog(null,
                    "Project saved !",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error writing to file!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }


    }
}
    private class ClearAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            txtProjectName.setText("");
            txtTeamLeader.setText("");
            txtStartDate.setText("");

            cmbTeamSize.setSelectedIndex(0);
            cmbProjectType.setSelectedIndex(0);
        }
    }
}



