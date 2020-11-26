package ro.mataoanu.logitech.coding;

import org.junit.Test;

import ro.mataoanu.logitech.coding.problem1.MasterPlan;

import static org.junit.Assert.*;

public class TestMasterPlan {
    @Test
    public void empty_array() {
        int[] test1 = {};

        assertEquals(0, MasterPlan.rob(test1));
    }

    @Test
    public void one_element_array() {
        int[] test1 = {2};

        assertEquals(2, MasterPlan.rob(test1));
    }

    @Test
    public void two_element_array() {
        int[] test1 = {2,3};
        assertEquals(3, MasterPlan.rob(test1));

        int[] test2 = {4,3};
        assertEquals(4, MasterPlan.rob(test2));
    }

    @Test
    public void three_element_array() {
        int[] test1 = {1,2,4};
        assertEquals(5, MasterPlan.rob(test1));

        int[] test2 = {4,3,2};
        assertEquals(6, MasterPlan.rob(test2));

        int[] test3 = {3,7,1};
        assertEquals(7, MasterPlan.rob(test3));
    }


    @Test
    public void example_element_array() {
        int[] test1 = {1,2,3,1};
        assertEquals(4, MasterPlan.rob(test1));
    }


    @Test
    public void skip_two_array() {
        int[] test;
        test = new int[]{3,1,1,3,1};
        assertEquals(6, MasterPlan.rob(test));

        test = new int[]{3,1,1,3,1};
        assertEquals(6, MasterPlan.rob(test));
    }

    @Test
    public void bigger_array() {
        int[] test1 = {3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1};
        assertEquals(36, MasterPlan.rob(test1));
    }
}