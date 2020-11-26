package ro.mataoanu.logitech.coding.problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Employee {
    private String name;
    private int age;
    private String employeeID;
    private String address;

    @Nullable
    private Employee boss;

    @NonNull
    private List<Employee> reports = new ArrayList<>();

    public Employee(String employeeID, String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.employeeID = employeeID;
        this.address = address;
    }

    public boolean isCEO() {
        return boss == null;
    }

    public boolean isEmployee(){
        return reports.isEmpty();
    }

    protected void addReport(Employee reporter){
        reports.add(reporter);
    }

    protected void addReports(List<Employee> reports ){
        for (Employee p:reports) {
            addReport(p);
        }
    }

    protected void clearReports(){
        this.reports.clear();
    }

    protected void removeReport(Employee reporter){
        reports.remove(reporter);
    }

    public void setBoss(Employee newBoss){
        if(boss!=null){
            boss.removeReport(this);
        }

        this.boss = newBoss;

        if(boss!=null){
            boss.addReport(this);
        }
    }

    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getReportsImmutable(){
        return Collections.unmodifiableList(reports);
    }

    public Employee getBoss() {
        return this.boss;
    }

}
