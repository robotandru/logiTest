package ro.mataoanu.logitech.coding;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import ro.mataoanu.logitech.coding.problem2.Employee;
import ro.mataoanu.logitech.coding.problem2.OrganisationalChart;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestOrganisationalChart {

    OrganisationalChart chart;

    ArrayList<Employee> employeesList;

    public void createOrganisation() {

        employeesList = new ArrayList<>();

        chart = new OrganisationalChart();
        Employee ceo = new Employee("id1", "name1", 21, "address");

        chart.setCEO(ceo);
        employeesList.add(ceo);

        Employee employee1 = new Employee("id2", "name2", 21, "address");
        chart.addEmployee(employee1, ceo);
        employeesList.add(employee1);

        Employee employee2 = new Employee("id3", "name2", 21, "address 2");
        chart.addEmployee(employee2,ceo);
        employeesList.add(employee2);

        Employee employee3 = new Employee("id4", "name3", 21, "address 3");
        chart.addEmployee(employee3,employee1);
        employeesList.add(employee3);

        Employee employee4 = new Employee("id5", "name4", 21, "address 4");
        chart.addEmployee(employee4,employee1);
        employeesList.add(employee4);
    }



    @Test
    public void Organisation_Should_HaveGeneralStructure(){
        createOrganisation();
        assertEquals( 2,chart.getCEO().getReportsImmutable().size());
        assertTrue(chart.getCEO().getReportsImmutable().containsAll(Arrays.asList(employeesList.get(1),employeesList.get(2))));
        assertTrue(employeesList.get(2).getReportsImmutable().isEmpty());
        assertEquals(2, employeesList.get(1).getReportsImmutable().size());
        assertTrue( employeesList.get(1).getReportsImmutable().containsAll(Arrays.asList(employeesList.get(3),employeesList.get(4))));
    }

    @Test
    public void Organisation_Should_FindCEO(){
        createOrganisation();
        assertEquals( 1,chart.findPersonByName("name1").size());
    }

    @Test
    public void Organisation_Should_FindOneEmployee(){
        createOrganisation();
        assertEquals( 1,chart.findPersonByName("name4").size());
    }

    @Test
    public void Organisation_Should_FindTwoEmployees(){
        createOrganisation();
        assertEquals( 2,chart.findPersonByName("name2").size());
    }

    @Test
    public void Organisation_Should_FindNoEmployees(){
        createOrganisation();
        assertEquals( 0,chart.findPersonByName("nameNotFound").size());
    }

    @Test
    public void Organisation_Should_Fail_on_ClearSearchResult(){
        createOrganisation();
        try {
            chart.findPersonByName("name3").clear();
            fail("search collection is not read only");
        } catch (UnsupportedOperationException e) {

        }
    }

    @Test
    public void Organisation_Should_Fail_on_ClearReports(){
        createOrganisation();
        try {
            chart.getCEO().getReportsImmutable().clear();
            fail("search collection is not read only");
        } catch (UnsupportedOperationException e) {

        }
    }


    @Test
    public void Organisation_Should_Fail_on_RemoveCEO(){
        createOrganisation();
        try {
            chart.removeEmployee(chart.getCEO());
        } catch (UnsupportedOperationException e) {

        }
    }

    @Test
    public void Organisation_Should_ReplaceCEO(){
        createOrganisation();

        Employee employee = new Employee("someId","replacementName",21,"");

        chart.replaceEmployee(chart.getCEO() , employee);

        assertEquals(0,chart.findPersonByName("name1").size());
        assertEquals(1,chart.findPersonByName("replacementName").size());
        assertEquals(chart.getCEO(), employee);

        assertTrue(chart.getCEO().getReportsImmutable().containsAll(Arrays.asList(employeesList.get(1),employeesList.get(2))));
    }

    @Test
    public void Organisation_Should_ReplaceEmployee(){
        createOrganisation();

        Employee newEmployee = new Employee("someId","replacementName",21,"");

        Employee employee4 = chart.findPersonByName("name4").iterator().next();

        chart.replaceEmployee(employee4 , newEmployee);

        assertEquals(0,chart.findPersonByName("name4").size());
        assertEquals(1,chart.findPersonByName("replacementName").size());
    }

    @Test
    public void Organisation_Should_RenameEmployee(){
        createOrganisation();

        Employee employee4 = chart.findPersonByName("name4").iterator().next();

        chart.renameEmployee(employee4 , "renamedEmployee");

        assertEquals(0,chart.findPersonByName("name4").size());
        assertEquals(1,chart.findPersonByName("renamedEmployee").size());
    }


}