import java.sql.Date;

class Visit {

    private int ID;
    private Date date;
    private String prescription;
    private int PatientID;
    private int DoctorID;
    private int price;

    public Visit(int iD, Date date, String prescription, int patientID, int doctorID, int price) {
        ID = iD;
        this.date = date;
        this.prescription = prescription;
        PatientID = patientID;
        DoctorID = doctorID;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public Date getDate() {
        return date;
    }

    public String getPrescription() {
        return prescription;
    }

    public int getPatientID() {
        return PatientID;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public int getPrice() {
        return price;
    } 
    

    
    
}
