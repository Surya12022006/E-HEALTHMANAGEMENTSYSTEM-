import java.awt.*; 
importjava.awt.event.*; 
importjava.util.ArrayList; 
class EHealthManagementSystemextendsFrameimplements ActionListener{ private 
TextField patientName, patientAge, patientCondition, searchField; private 
TextArea displayArea; 
privateArrayList<Patient>patients; 
publicEHealthManagementSystem(){ 
// Initialize patient list 
patients=newArrayList<>(); 
// Set layout and title 
setLayout(newBorderLayout()); 
setTitle("E-HealthManagementSystem"); 
//Addcolorfulheaderwitha hospitalicon 
PanelheaderPanel=newPanel(newBorderLayout()); 
headerPanel.setBackground(Color.CYAN); 
LabelheaderLabel= 
new Label("E-HealthManagement System", 
Label.CENTER); 
headerLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
headerPanel.add(newHospitalIcon(),BorderLayout.WEST); 
headerPanel.add(headerLabel, BorderLayout.CENTER); 
add(headerPanel, BorderLayout.NORTH); 
//Createinputpanel 
PanelinputPanel=newPanel(newGridLayout(4,2)); 
inputPanel.add(new Label("Patient Name:")); 
patientName = new TextField(20); 
inputPanel.add(patientName); 
inputPanel.add(newLabel("PatientAge:")); 
patientAge = new TextField(20); 
inputPanel.add(patientAge); 
inputPanel.add(newLabel("Condition:")); 
patientCondition = new TextField(20); 
inputPanel.add(patientCondition); 
ButtonaddButton=newButton("AddPatient"); 
addButton.setBackground(Color.GREEN); 
addButton.setForeground(Color.WHITE); 
addButton.addActionListener(this); 
inputPanel.add(addButton); 
ButtonviewAllButton=newButton("ViewAllPatients"); 
viewAllButton.setBackground(Color.BLUE); 
viewAllButton.setForeground(Color.WHITE); 
viewAllButton.addActionListener(this); 
inputPanel.add(viewAllButton);
PanelsearchPanel=newPanel(newFlowLayout()); 
searchPanel.add(new Label("Search by Name:")); 
searchField = new TextField(20); 
searchPanel.add(searchField); 
Button searchButton = new Button("Search"); 
searchButton.setBackground(Color.ORANGE); 
searchButton.setForeground(Color.WHITE); 
searchButton.addActionListener(this); 
searchPanel.add(searchButton); 
//Displayarea 
displayArea = new TextArea(10, 50); 
displayArea.setBackground(Color.LIGHT_GRAY); 
displayArea.setFont(newFont("Monospaced",Font.PLAIN,14)); 
displayArea.setEditable(false); 
// Add components to the frame 
add(inputPanel, BorderLayout.WEST); 
add(searchPanel, BorderLayout.CENTER); 
add(displayArea, BorderLayout.SOUTH); 
//Frameproperties 
setSize(700, 500); 
setVisible(true); 
// Close window event 
addWindowListener(newWindowAdapter(){
    publicvoidwindowClosing(WindowEvente){ 
        System.exit(0); 
        } 
        }); 
        } 
        publicvoidactionPerformed(ActionEvente){ 
        Stringcommand=e.getActionCommand(); 
        if (command.equals("Add Patient")) { 
        String name = patientName.getText(); 
        StringageText=patientAge.getText(); 
        Stringcondition=patientCondition.getText(); 
        if(name.isEmpty()||ageText.isEmpty()||condition.isEmpty()){ 
        displayArea.setText("All fields are required!"); 
        return; 
        } 
        try { 
        int age = Integer.parseInt(ageText); 
        patients.add(new Patient(name, age, condition)); 
        displayArea.setText("Patientaddedsuccessfully!"); 
        patientName.setText(""); 
        patientAge.setText(""); 
        patientCondition.setText(""); 
        } catch (NumberFormatException ex) { 
        displayArea.setText("Agemustbeavalidnumber!");
    } 
}elseif(command.equals("ViewAllPatients")){ if 
(patients.isEmpty()) { 
displayArea.setText("Nopatientsavailable."); 
}else{ 
StringBuilderbuilder=newStringBuilder(); for 
(Patient p : patients) { 
builder.append(p).append("\n"); 
} 
displayArea.setText(builder.toString()); 
} 
}elseif(command.equals("Search")){ 
StringsearchName=searchField.getText(); if 
(searchName.isEmpty()) { 
displayArea.setText("Pleaseenteranametosearch!"); return; 
} 
booleanfound= false; 
StringBuilderbuilder=newStringBuilder(); 
for (Patient p : patients) { 
if(p.getName().equalsIgnoreCase(searchName)){ 
builder.append(p).append("\n"); 
found=true; 
} 
} 
if (found) { 
displayArea.setText(builder.toString()); 
}else{ 
displayArea.setText("Nopatientfoundwiththename:"+searchName); 
} 
} 
} 
//InnerclasstorepresentaPatient class 
Patient { 
privateStringname; private 
int age; 
privateStringcondition; 
publicPatient(Stringname,intage,Stringcondition){ 
this.name = name; 
this.age = age; 
this.condition=condition; 
} 
publicStringgetName(){ 
return name; 
} 
publicStringtoString(){ 
return"Name:"+name+",Age:"+age+",Condition:"+condition; 
} 
} 
//Customcanvasforthehospitalicon 
class HospitalIcon extends Canvas { 
publicvoidpaint(Graphicsg){ 
g.setColor(Color.RED); 
g.fillRect(20, 20, 50, 50); 
g.setColor(Color.WHITE); 
g.fillRect(40, 30, 10, 30); 
g.fillRect(30,40,30,10); 
} 
publicDimensiongetPreferredSize(){ 
return new Dimension(90, 90); 
} 
} 
publicstaticvoidmain(String[]args){ new 
EHealthManagementSystem(); 
} 
}