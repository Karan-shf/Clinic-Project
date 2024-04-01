public interface IDataBase {

    static void InsertVisit(Visit visit){}

    static void InsertNurse(Nurse nurse){}

    static void InsertDoctor(Doctor doctor){}

    static void InsertPersonel(Personel personel){}

    static void ImportDoctors(boolean filter){}

    static void ImportNurses(boolean filter){}

    static void ImportPatients(){}

    static void ImportPersonel(boolean filter){}

    static void ImportVisits(){}

    static Admin Extract_Admin(int primary_key){return null;}

    static void Import_Filtered_Visits(int EmployeeID , String column ){}

    static Doctor Extract_Doctor(int primary_key){return null;}

    static Nurse Extract_Nurse(int primary_key) {return null;}

    static Personel Extract_Personel(int primary_key){return null;}

    static Patient Extract_Patient(int primary_key){return null;}

    static void Update(String table_name,String updatedParameter,String updatedValue,int ID){}

    static void Update(String table_name,String updatedParameter,int updatedValue,int ID){}

    static void Update(String table_name,String updatedParameter,boolean updatedValue,int ID){}

    static void Delete(String table_name,int ID){}

} 