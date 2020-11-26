repo description:
 - there are two packages unimaginatively named:
	ro.mataoanu.logitech.coding.problem1
	ro.mataoanu.logitech.coding.problem2
	
PROBLEM 1
- the solution to the problem is provided by the MasterPlan.rob(int[] houses)

at first glance, I thought that going for the first or second house and skipping one  and choosing between the two end sums would solve it
the I saw that maybe skipping two houses made sense.
following this reasoning, why not skip 3? but skipping 3 houses makes no sense because not robbing the one in the middle would leave money on the table

so in the end it boils down to choosing to skip one or two houses

- there is an unit test file named TestMasterPlan file under the test folder for a few cases like: 
	- 1 house, 2 houses, 3 houses 
	- a case where the solution has to skip 2 houses, 
	- the provided test case
	- a longer array of houses
	
	
PROBLEM 2

- there are two classes Employee and OrganisationalChart
Assumptions:
- I had a bit of doubt regarding the current reports meaning: " a document that presents information in an organized format for a specific audience and purpose" or because the name was similar to current boss, I assumed it reffers to subbordinates in the organisational chart, so I went with this one.
- At first I thought that the return of findPersonByName should only pe an element, but it would be absurd to require that employees have unique names so I made it return a collection
- EmployeeID could be generated but I think that it should be the responsibility of another class or program. Also, if this were a true program, the uniqueness of this ID should be tested, I chose not to do it for the assignment, but could be implemented by also maintaining a setmap with ids in the OrganisationalChart class and maybe making the id readOnly (should not provide a setter for it)


for faster find by name I used guava's Multimap<String, Employee> as  an index
because of this index I needed to move some of the functionality from the employee to the OrganisationalChart in order to maintain the index up to date and prevent corruption

in order to prevent undesired use some of the methods return an immutable list: OrganisationalChart.findPersonByName and Employee.getReportsImmutable
findPersonByName return a collection

this implementation with an index brings a lot of complexity (I could have regenerated the index after some of the opperations but I think it would have moved the cost from serch to modify and I wanted that kept in check too.

some of the corner cases are covered by tests that I hope are self explanatory.
for testing there is a TestOrganisationalChart unitest that runs a few tests that I hope are self:
TestOrganisationalChart.Organisation_Should_HaveGeneralStructure
TestOrganisationalChart.Organisation_Should_ReplaceCEO
TestOrganisationalChart.Organisation_Should_FindNoEmployees
TestOrganisationalChart.Organisation_Should_RenameEmployee
TestOrganisationalChart.Organisation_Should_ReplaceEmployee
TestOrganisationalChart.Organisation_Should_FindTwoEmployees
TestOrganisationalChart.Organisation_Should_FindOneEmployee
TestOrganisationalChart.Organisation_Should_FindCEO
TestOrganisationalChart.Organisation_Should_Fail_on_RemoveCEO
TestOrganisationalChart.Organisation_Should_Fail_on_ClearSearchResult
TestOrganisationalChart.Organisation_Should_Fail_on_ClearReports



for running all is needed is to run the unit tests 