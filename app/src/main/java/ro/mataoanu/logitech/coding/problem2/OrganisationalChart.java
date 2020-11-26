package ro.mataoanu.logitech.coding.problem2;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Collections;

import androidx.annotation.NonNull;

public class OrganisationalChart {
    Multimap<String, Employee> organisationChart = ArrayListMultimap.create();

    Employee ceo;

    /*
    if there is already a CEO use replace
     */
    public void setCEO(@NonNull Employee employee){
        if(ceo==null){
            organisationChart.put(employee.getName(), employee);
            this.ceo = employee;
        } else {
            replaceEmployee(ceo, employee);
        }
    }

    public Employee getCEO(){
        return ceo;
    }

    public void addEmployee(@NonNull Employee employee, @NonNull Employee boss) {
        employee.setBoss(boss);
        organisationChart.put(employee.getName(), employee);
    }

    /*
    replaces the initial employee in the chart and transfers it's boss and reporters
    it returns the replaced employee stripped of boss and reporters so it may be placed in another area of the organisation
     */

    public Employee replaceEmployee(@NonNull Employee initialEmployee, @NonNull Employee replacement){

        replacement.setBoss(initialEmployee.getBoss());
        replacement.addReports(initialEmployee.getReportsImmutable());

        organisationChart.put(replacement.getName(), replacement);
        organisationChart.remove(initialEmployee.getName(), initialEmployee);

        if(initialEmployee.isCEO()){
            ceo = replacement;
        }

        initialEmployee.setBoss(null);
        initialEmployee.clearReports();

        return initialEmployee;
    }

    /*
    remove an employee
    -if they have subordinates they will be transferred to the higher boss
    -if they are ceo and has subordinates, they can;t be removed only replaced
     */
    public void removeEmployee(Employee employee) {

        if(employee.getBoss()!=null){
            employee.getBoss().addReports(employee.getReportsImmutable());
            employee.clearReports();

            organisationChart.remove(employee.getName(), employee);
        } else {
            throw new UnsupportedOperationException("Can't remove CEO");
        }
    }

    /*
    here we actually recreate the employee. maybe it would be better to just rename it,
    but I though that reusing the replace functionality would also be good, the downside is that we create a new object

    the function recreates the employee and places it in the organisation and also returns the new object
     */
    public Employee renameEmployee(Employee employee, String newName) {
        Employee renamedEmployee = new Employee(employee.getEmployeeID(), newName, employee.getAge(), employee.getAddress());
        replaceEmployee(employee, renamedEmployee);

        return renamedEmployee;
    }

    public Collection<Employee> findPersonByName(String name) {
        return Collections.unmodifiableCollection(organisationChart.get(name));
    }

}
