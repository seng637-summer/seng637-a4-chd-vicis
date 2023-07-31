**SENG 637 - Dependability and Reliability of Software Systems**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group: SENG637- 2   |
|-----------------|
| Jash Dubal                |   
| Steven Duong              |   
| Nikhil Naikar               |   
| Jason Xu                |
| Christopher DiMattia                |


# Introduction
This report presents the findings of the team's work on mutation testing and automated web app testing. The team focused on applying mutation testing to the Range and DataUtilties classes and performed web app testing on the HomeDepot website using the Selenium library. Throughout the process, the team encountered challenges and learned valuable lessons related to both mutation testing and automated testing, which are discussed in detail below.

# Analysis of 10 Mutants of the Range class 
1. Mutation #1 (on line #123, mutation #5)<br>
  Mutation applied by Pitest tool - Replaced double subtraction with addition on method Range.getLength(). This mutant was applied on the following line:
  ```
  return this.upper - this.lower;
  ```
  This mutation was an arithmetic operator replacement since it replaced the "-" with "+", adding the two variables instead of subtracting them, affecting the returned value. This mutant was successfully killed by some of the test cases. For example, one test case that killed this mutant was testGetLengthValidRange(), fed in a range with the lower bound being -1 and the upper bound being 1 and expected this function getLength() to return  2 (1 - (-1) = 2). Therefore, since this mutant was introduced the result ended up being 0 (1 + (-1) = 0) and thus resulted in this test case failing, which means the mutant was killed.<br>
  
2. Mutation #2 (on line #123, mutation #1)<br>
  Mutation applied by Pitest tool - Incremented (a++) double field upper on method Range.getLength(). This mutant was applied on the following line:
  ```
  return this.upper - this.lower;
  ```
  This mutation was including a post-increment operation to the variable upper. However, since the above line of code will still use the original value of the upper for the subtraction and this variable is only used once in the above line of code, thus this post-increment operation has no effect on the returned value. Therefore, this mutation introduces an equivalent mutant that can not be killed and thus survived.<br>

3. Mutation #3 (on line #105, mutation #1)<br>
  Mutation applied by Pitest tool - Incremented (a++) double field lower on method Range.getLowerBound(). This mutant was applied on the following line:
  ```
  return this.lower;
  ```
  This mutation was including a post-increment operation to the variable lower. However, since the above line of code will still return the original value of the lower variable and this variable is only used once in the above line of code, thus this post-increment operation has no effect on the returned value. Therefore, this mutation introduces an equivalent mutant that can not be killed and thus survived.<br>

4. Mutation #4 (on line #114, mutation #6)<br>
  Mutation applied by Pitest tool - Incremented (++a) double field upper on method Range.getUpperBound(). This mutant was applied on the following line:
  ```
  return this.upper;
  ```
  This mutation was including a pre-increment operation to the variable upper. The above line of code will now return the original value incremented by 1, affecting the return value. This mutant was successfully killed by some of the test cases. For example, one test case that killed this mutant was getUpperBoundWithSameValue(), fed in a range with the upper bound of 0.5 and expected this function getUpperBound() to return 0.5. But, since this mutant was introduced the returned value ended up being 1.5 (++0.5 => 0.5 + 1 = 1.5) and thus resulted in this test case failing, which means the mutant was killed.<br>

5. Mutation #5 (on line #217, mutation #4)<br>
  Mutation applied by Pitest tool - removed conditional, replaced equality check with true on method Range.combine(Range range1, Range range2). This mutant was applied on the following line:
  ```
  if (range1 == null)
  ```
  This mutation removed the conditional check and always made the condition output true. The above line of code will now result in the range2 always being returned, affecting the return value. This mutant was successfully killed by some of the test cases. For example, one test case that killed this mutant was combineWithAUB(), fed in a range1(0.0, 1.1) and a range2(-1.0, 1.0), expected this function combine(Range range1, Range range2) to return a range(-1.0, 1.1). But, since this mutant was introduced the returned value ended up being a range(-1.0, 1.0) (which is just range2) and thus resulted in this test case failing, which means the mutant was killed.<br>

6. Mutation #6 (on line #223, mutation #1)<br>
  Mutation applied by Pitest tool - replaced call to java/lang/Math::min with argument on method Range.combine(Range range1, Range range2). This mutant was applied on the following line:
  ```
  double l = Math.min(range1.getLowerBound(), range2.getLowerBound());
  ```
  This mutation removed the Math.min function and with assumption assigned variable l either the first argument (range1.getLowerBound()) or the second (range2.getLowerBound()). This mutant does affect the returned value and could potentially return an incorrect result. However, this mutant managed to survive, meaning all my test cases for this method passed. Therefore, more test cases need to be added to kill this mutant.<br>

7. Mutation #7 (on line #223, mutation #2 & #3)<br>
  Mutation applied by Pitest tool  - removed call to org/jfree/data/Range::getLowerBound on method Range.combine(Range range1, Range range2). This mutant was applied on the following line:
  ```
  double l = Math.min(range1.getLowerBound(), range2.getLowerBound());
  ```
  This mutation affects the returned value and could potentially return an incorrect result. Also, this same mutation happened twice but strangely the first time it survived, and the second time it was killed. Therefore, more test cases need to be added to ensure that all occurrences of this type of mutant are caught and killed.<br> 

8. Mutation #8 (on line #225, mutation #1)<br>
  Mutation applied by Pitest tool - Incremented (a++) double local variable number 2 on method Range.combine(Range range1, Range range2). This mutant was applied on the following line:
  ```
  return new Range(l, u);
  ```
  This mutation was including a post-increment operation to the variable l (assumption not u, not sure what local variable 2 is). However, since the above line of code will still return the original value of l and this variable is only used once in the above line of code, thus this post-increment operation has no effect on the returned value. Therefore, this mutation introduces an equivalent mutant that can not be killed and thus survived.<br>  

9. Mutation #9 (on line #225, mutation #4)<br>
  Mutation applied by Pitest tool - Decremented (a--) double local variable number 4 on method Range.combine(Range range1, Range range2). This mutant was applied on the following line:
  ```
  return new Range(l, u);
  ```
  This mutation was including a post-decrement operation to the variable u (assumption not l, not sure what local variable 4 is). However, since the above line of code will still return the original value of u and this variable is only used once in the above line of code, thus this post-decrement operation has no effect on the returned value. Therefore, this mutation introduces an equivalent mutant that can not be killed and thus survived.<br>   

10. Mutation #10 (on line #475, mutation #1)<br>
  Mutation applied by Pitest tool - Incremented (a++) double field lower on method Range.toString(). This mutant was applied on the following line:
  ```
  return ("Range[" + this.lower + "," + this.upper + "]");
  ```
  This mutation was including a post-increment operation to the variable lower. However, since the above line of code will still return the original value of lower and this variable is only used once in the above line of code, thus this post-increment operation has no effect on the returned value. Therefore, this mutation introduces an equivalent mutant that can not be killed and thus survived.<br>   

  
# Report all the statistics and the mutation score for each test class
- Mutation Score of Range Class - Before<br>
  <img width="600" alt="Screenshot 2023-07-26 at 5 24 32 PM" src="https://github.com/chd-vicis/seng637-a4/assets/61436662/88955810-62c9-4fc0-ab4a-37eea388738f"><br>
- Mutation Statistics of Range Class - Before<br>
  <img width="300" alt="Screenshot 2023-07-26 at 5 25 34 PM" src="https://github.com/chd-vicis/seng637-a4/assets/61436662/c591613f-c6f5-47a4-bf51-5d1d9dcf99cb"><br>
  The following table shows the mutation score for each of the 5 tested methods.
  |Tested Method| # of Survived Mutants| # of Killed Mutants | Total | Mutation Score |
  |-------------|----------------------|---------------------|-------|----------------|
  |Range.combine(Range range1, Range range2)| 6 | 27 | 33 | 82 %|
  |Range.toString()| 4 | 18 | 22 | 82% |
  |Range.getLength()| 4 | 15 | 19 | 79% |
  |Range.getLowerBound()| 1 | 6 | 7 | 86% |
  |Range.getUpperBound()| 1 | 6 | 7 | 86% |
  
- Mutation Score of Range Class - After<br>
  <img width="600" alt="Screenshot 2023-07-29 at 11 57 57 AM" src="https://github.com/chd-vicis/seng637-a4/assets/61436662/4596e3e6-b55f-44d0-9c02-d5797f5dbaae">

- Mutation Statistics of Range Class - After<br>
   <img width="300" alt="Screenshot 2023-07-29 at 11 57 38 AM" src="https://github.com/chd-vicis/seng637-a4/assets/61436662/6c910a28-34d0-4ac9-9530-0305fe198fd6">

  The following table shows the mutation score for each of the 5 tested methods.
  Note: The mutation score could not be improved enough by just adding more test cases for the original 5 test methods. This is because the original test cases managed to kill almost every mutant but the equivalent mutants. Therefore, test cases for two more methods were added to improve the mutation score.
  |Tested Method| # of Survived Mutants| # of Killed Mutants | Total | Mutation Score |
  |-------------|----------------------|---------------------|-------|----------------|
  |Range.combine(Range range1, Range range2)| 4 | 29 | 33 | 88 %|
  |Range.toString()                         | 4 | 18 | 22 | 82% |
  |Range.getLength()                        | 4 | 15 | 19 | 79% |
  |Range.getLowerBound()                    | 1 | 6 | 7 | 86% |
  |Range.getUpperBound()                    | 1 | 6 | 7 | 86% |
  |Range.contains(double value)             | 8 | 45 | 53 | 85% |
  |Range.intersects(double b0, double b1)   | 22 | 84 | 106 | 79% |

- Mutation Score of DataUtilities Class - Before<br>
  <img width="600" alt="Screenshot 2023-07-30 at 3 18 44 PM" src="https://github.com/chd-vicis/seng637-a4/assets/61436662/ff9ce15f-fe26-450b-8283-4d5f3c477276"><br>
- Mutation Statistics of DataUtilities Class - Before<br>
  <img width="300" alt="Screenshot 2023-07-30 at 3 19 12 PM" src="https://github.com/chd-vicis/seng637-a4/assets/61436662/44dd855e-c9e2-424b-b105-8e95e91876fd"><br>
  The following table shows the mutation score for each of the 5 tested methods.
  |Tested Method| # of Survived Mutants| # of Killed Mutants | Total | Mutation Score |
  |-------------|----------------------|---------------------|-------|----------------|
  |DataUtilities.calculateColumnTotal(Values2D data, int column) | 6 | 61 | 67 | 91%|
  |DataUtilities.calculateRowTotal(Values2D data, int row)       | 6 | 61 | 67 | 91% |
  |DataUtilities.createNumberArray(double[] data)                | 4 | 34 | 38 | 89% |
  |DataUtilities.createNumberArray2D(double[][] data)            | 2 | 42 | 44 | 95% |
  |DataUtilities.getCumulativePercentages(KeyedValues data)      | 8 | 117 | 125 | 94% |
  
- Mutation Score of DataUtilities Class - After<br>
  <img width="810" alt="Screenshot 2023-07-30 at 4 23 59 PM" src="https://github.com/chd-vicis/seng637-a4/assets/61436662/32689fdd-1d67-4f7d-9057-73b8de6a24d3"><br>
- Mutation Statistics of DataUtilities Class - After<br>
  <img width="200" alt="Screenshot 2023-07-30 at 4 24 15 PM" src="https://github.com/chd-vicis/seng637-a4/assets/61436662/7d41a5bd-ba6d-4c57-b950-c8303e286a6a"><br>
  The following table shows the mutation score for each of the 5 tested methods. Note: The mutation score could not be improved enough by just adding more test cases for the original 5 test methods. This is because the original test cases managed to kill almost every mutant but the equivalent mutants. Therefore, test cases for one more method were added to improve the mutation score.
  |Tested Method| # of Survived Mutants| # of Killed Mutants | Total | Mutation Score |
  |-------------|----------------------|---------------------|-------|----------------|
  |DataUtilities.calculateColumnTotal(Values2D data, int column) | 6 | 61 | 67 | 91%|
  |DataUtilities.calculateRowTotal(Values2D data, int row)       | 6 | 61 | 67 | 91% |
  |DataUtilities.createNumberArray(double[] data)                | 4 | 34 | 38 | 89% |
  |DataUtilities.createNumberArray2D(double[][] data)            | 2 | 42 | 44 | 95% |
  |DataUtilities.getCumulativePercentages(KeyedValues data)      | 8 | 117 | 125 | 94% |
  |DataUtilities.ccalculateColumnTotal(Values2D data, int column, int[] validRows) | 15 | 76 | 91 | 84%|
  
  
  
# Analysis drawn on the effectiveness of each of the test classes
The effectiveness of each method was significantly improved if it had no previous test cases, or only slightly improved if it already had existing test cases from previous assignments. The mutation score for methods with previous test cases was already high (79-86%) due to extensive testing in earlier assignments. However, the majority of the mutants that were not killed were equivalent mutants, which by definition, do not affect the software's functionality and are therefore very difficult to eliminate.  The equivalent mutants made up all the unkillable mutants after the team could no longer improve their mutant score.

Due to the difficultly in reaching the 10% improvement requirement from the assignment outline the team decided to include other functions within the tested classes to reach a higher overall test score. Interestingly, the other methods also achieved peak test scores in the 79-86% range, indicating that equivalent mutants accounted for roughly 14-21% of the mutants.  Overall the team waas able to easily identify why certain mutants survived and if the mutant was killable or an equivalent mutant.  The team also managed to obtain  high mutation scores with the lowest being 79% and high overall scores typically in the mid 80s and 90s. 

# A discussion on the effect of equivalent mutants on mutation score accuracy
Equivalent mutants had a negative impact on the mutation score. As mentioned above these mutants by definition, do not affect the behavior of the SUT, making it impossible to eliminate them easily. A prominent example as mentioned above was the post-increment operator, which cannot be easily killed because it only affects the return value of a method after it is already tested.  More generally, dealing with equivalent mutants poses significant challenges because they require more complex testing approaches or the creation of tests beyond the usual scope. One possible way to catch an equivalent mutant like the post-increment operator is to design a test that not only returns the function but also utilizes its output in subsequent operations which is discussed more below. However, this approach leads to the expansion of test cases beyond the typical domain in which they are tested, making it less practical.

Manual review is another approach to handle equivalent mutants. The team would carefully assess the impact of equivalent mutants on the program. However, this method has its drawbacks as it can be time consuming, tedious, error-prone, and may lead to misjudgments due to factors like testers lacking contextual knowledge of the program or changes in the program's scope. Additionally, human factors such as fatigue can also contribute to errors in the review process.

In general equivalent mutants will lower the mutation score and it is recommended that more advanced testing be applied to kill mutants such as writing tests that may have an expanded scope, limit the types of mutant operators that can be applied to a test or apply more complex analysis of mutant results to isolate mutant equivalents and remove them from the mutant score.  Upon researching how to kill equivalent mutants the team found several interesting papers and software suites but the two most interesting papers are listed below.
1. https://www.sciencedirect.com/science/article/pii/S0167642314002603
2. https://www.sciencedirect.com/science/article/pii/S0167642314002603


# A discussion of what could have been done to improve the mutation score of the test suites
As mentioned earlier, the mutation test scores for the methods covered in previous assignments had very few non-equivalent mutants. The team faced significant challenges in improving the mutant test score by more than 10%. Consequently, the team decided to address this issue by adding additional tests to the Range and DataUtiltites class, which resulted in an overall improvement in the test score.

The primary obstacle was dealing with equivalent mutants, which could not be eliminated without employing much more complex test cases. However, for non-equivalent mutants, the team adopted a straightforward approach. We thoroughly analyzed each mutant and then introduced additional tests based on the method's functionality to effectively detect and eliminate the non-equivalent mutants.

One of the suggestions brought up by the team to detect equivalent mutants was to combine test cases with simple operations or even other test cases. For example, when testing the "getUpperBound" method, the team could have designed a test to check if the upper bound was returned correctly after being added to a known value. This approach could have helped catch the equivalent mutant that involves post increment operators.  However, implementing such testing can lead to complicating the test cases and may violate the principle that tests should focus solely on their immediate functionality. In other words, tests should not be designed to verify unrelated methods, regardless of how straightforward they are. This could create unnecessary dependencies between tests and reduce the maintainability and clarity of the test suite. Therefore, the team did not pursue this approach, opting to find other ways to improve the mutation score without compromising the test suite's integrity.


# Why do we need mutation testing? Advantages and disadvantages of mutation testing

Advantages
1. Automation: Mutant testing is automated, enabling quick execution compared to exploratory or manual testing
2. Fault Detection: It provides an efficient way to detect faults in the code because it is white-box, so error are found right away compared to black-box testing
3. Benchmarking: Mutation testing is useful for benchmarking other testing methods. A high mutation test score indicates well-designed tests, and analyzing test suites using various testing methods enhances the tester's confidence in having a comprehensive test suite.

Disadvantages
1. Impact of Equivalent Mutations: Equivalent mutations can skew scores and make it challenging to interpret test results without an in-depth understanding of the test suite and the applied mutants
2. Manual Review for Equivalent Mutants: Identifying killable mutants versus equivalent mutants often requires manual review, which is time-consuming and susceptible to human error
3. Computational Expense: Mutation testing can be computationally expensive compared to other test suites because each test is effectively executed multiple times. While this may not be an issue for small test suites like in this assignment, it can become impractical and time-consuming for larger test suites

# Explain your SELENUIM test case design process
Our test case design process started with a clear understanding of the application's functionality and the specific actions or operations we needed to validate. This understanding was fundamental to ensuring we can effectively simulate the end-user's behavior.

The Selenium test case design process involves the following steps:

1. **Defining the Scope of Testing:** Before we began with the design process, we identified the functionalities of the Home Depot website that were critical and required testing. This helped us narrow down our focus and design meaningful test cases. The 10 functionalities we decided to test included Customer Reviews, Search bar, Shop by Rooms, Shop by Department, Warehouse Value & Specials, Sign In, Add Item to Cart, Change Store, Find Home Service, Search in Weekly Flyer.

2. **Test Recording:** After defining our scope, we moved on to recording our tests. This was performed using Selenium IDE's 'Record' function. With the record feature turned on, we navigated to the Home Depot site in our browser and began performing the various actions corresponding to our scope. For instance, we searched for a product, navigated to its details, added it to the cart, and proceeded to checkout. Selenium IDE recorded all these interactions.

3. **Inserting Assertions:** Once the actions were recorded, we inserted assertions in our test cases at crucial points. Assertions are used to validate the state of the application with what's expected. For example, we might assert the presence of an element, the content of a field, the current URL, or the title of the page.

4. **Test Playback:** After the test was recorded and assertions were inserted, we replayed the test to validate the correctness of the test and to see if it's working as expected. This was done using the 'Play' feature in Selenium IDE.

5. **Modifying and Refining Test Cases:** Often, the recorded test may not work flawlessly in the first go. We encountered situations where we needed to adjust timing (wait for a certain element to load), modify selectors (to make them more reliable), or reorder some steps. We refined our tests in these cases to ensure they were robust and reliable.

6. **Testing with Different Data:** Finally, we ran our tests with different sets of data. For instance, we tested the login functionality with both valid and invalid credentials to ensure the functionality behaves correctly in both cases.

# Explain the use of assertions and checkpoints

Assertions and checkpoints are vital components of our testing process, utilized to confirm if a software or website behaves as intended. They function as validation checks, validating whether particular conditions or outcomes are achieved during the testing procedure.

**Assertions:** These are the conditions that we are checking during our test execution. For instance, in the `VerifyProductReviews` test, we're asserting (using assert text) that the text of the reviews displayed matches the text we expect for the specific product ("DEWALT 20V MAX XR Cordless Brushless 3" Cut-Off Tool (tool-only)"). In the `NavigateToProductReviews` test, we're asserting (using assert element present) that the "Customer Reviews" element is present on the page. Basically, assertions help us to determine whether a test has passed or failed based on the boolean outcome (true or false) of the condition checked.

**Verification Checkpoints:** These are the anticipated outcomes that our assertions aim to verify. For example, in the `NavigateToShopByRoom` test, the verification checkpoint is that the page successfully navigates to the "Shop by Room" section. This is what we expect to happen when the test is executed correctly, and it's what we're confirming with our assert title command. Similarly, for the `SelectSpecificRoom` test, the verification checkpoint is the successful navigation to the "Kitchen" category under "Shop by Room". Each verification checkpoint aligns with a specific assertion in our test, serving as an indicator of the test's success.


| Name of Test               | Command (Assert)                   | Verification Checkpoints                                                        |
|----------------------------|---------------------------|---------------------------------------------------------------------------------|
| `VerifyProductReviews` | `assert element present` | verifies if the reviews displayed belong to the correct product (DEWALT 20V MAX XR Cordless Brushless 3" Cut-Off Tool (tool-only)) |
| `NavigateToProductReviews` | `assert text` | verifies the presence of "Customer Reviews" |
| `SearchItem` | `assert text` | verifies the presence of "Milwaukee Tool PACKOUT 22-inch Rolling Tool Box" after searching for it |
| `SearchInvalidItem` | `assert text` | verifies the presence of "computer monitor" in the text of the queried items |
| `NavigateToShopByRoom` | `assert title` | The page should successfully navigate to the "Shop by Room" section |
| `SelectSpecificRoom` | `assert title` | The page should successfully navigate to the "Kitchen" category under "Shop by Room" |
| `DepartmentMenu` | `assert element present` | The page should successfully display a dropdown menu with various departments |
| `SelectSpecificItem` | `assert title` | The page should successfully navigate to the "Air Purifiers & Filters" items category under the "Heating & Cooling" department|
| `ShopInWarehouseValueAndSpecials` | `assert title` | The page should successfully navigate to the Warehouse Value and Specials page |
| `SaleInWarehouseValueAndSpecials` | `assert element present` | The items chosen products listed in this section should have an indicator of being on sale (such as a discounted price, "sale" or "discount" label)
| `SignInNavigate`                 | `assert element present`           | The page should successfully navigate to the "Sign In" section                                                    |
| `SignInValidDetails`                  | `assert text`                      | Verifies user information in account details after signing in
| `SignInInvalidDetails`                  | `assert text`                      | Informs user that credientials provided were incorrect
| `AddItemNavigate`                | `assert title`                     | The page should successfully navigate to the item's detail page                                                   |
| `AddItemToCart`                  | `assert text`           | Verifies that the "Add to Cart" button is present and can be clicked                                              |
| `ChangeStoreNavigate`            | `assert title`                     | The page should successfully navigate to the "Change Store" section                                               |                                      |
| `ChangeStoreSelection`           | `assert text`                      | Verifies that the first store from the search results matches the address provided                                |
| `HomeServicesNavigate`   | `assert title`                     | The page should successfully navigate to the "Home Services" section                                               |
| `FindHomeService`           | `assert element present`           | Finds contact information for the Home Service after searching |
| `SearchWeeklyFlyerNavigate`      | `assert title`                     | The page should successfully navigate to the "Weekly Flyer" section                                               |
| `SearchValidItemWeeklyFlyer`     | `assert text`                      | Verifies that the valid item appears in the search results                                                        |
| `SearchInvalidItemWeeklyFlyer`   | `assert text`                      | Verifies that the invalid item does not appear in the search results and an appropriate error message is displayed |


# how did you test each functionality with different test data

We selected 10 key functionalities of the Home Depot website to test thoroughly. For each functionality, we developed test cases using Selenium IDE. The table below covers the test cases that we developed:

| Functionality         | Test Cases                                                            |
|-----------------------|-----------------------------------------------------------------------|
| Customer Reviews      | Test if the reviews belong to the correct product                            |
|              | Test the presence of customer reviews for a patio item                       |
| Search bar   | Test search for an item that does not exist using "computer monitor"         |
|              | Test for a specified toolbox to search                                       |
| Shop by Room | Test if the "Shop by Room" page appears after clicking on the nav link        |
|              | Test if kitchen furnishings and appliances appear when clicking on "Kitchen" |
| Shop by Department | Test if the "Shop by Department" dropdown appears after clicking on the nav link        |
|              | Test if "Air Purifiers & Filters" appear when routing to "Heating & Cooling" -> "Air Purifiers & Filters" |
| Warehouse Value & Specials | Test if the page navigates to the Warehouse Value & Specials page |
|                               | Test if items under "Savings Lighting & Ceiling Fans" are on sale        |
| Sign In                       | Test if the "Sign In" form can be accessed from "account" page                                   |
|                               | Test if user can sign in by entering valid details (e.g., username, password)         |
| Add Item to Cart              | Test if the product detail page appears after clicking on a specific item                            |
|                               | Test if the item can be added to the cart by clicking on the "Add to Cart" button                    |
| Change Store                  | Test if the "Change Store" page appears after clicking on the nav link                               |
|                               | Test if user can enter a new location (e.g., address, city, postal code, etc.)                       |
|                               | Test if user can select the first store from the search results based on the new location            |
| Find Home Service     | Test if the "Home Services" page appears and offeres a search option after visiting page                               |
|                               | Test if we can get information about the specified Home Service |
| Search in Weekly Flyer        | Test if the "Weekly Flyer" page appears after visiting page                               |
|                               | Test if user can search for a valid item (e.g., "Garden Hose") and see the item in the search results |
|                               | Test if user can search for an invalid item (e.g., "Alien Spaceship") and receive any results   |

# Discuss advantages and disadvantages of Selenium vs. Sikulix

## Selenium

### Advantages:

- Open Source and Popular: Selenium is an open-source tool, which means it enjoys significant support from a broad community of users. This popularity ensures a wealth of resources and peer support.
- Extendable: You can expand Selenium's functionality through the use of plugins. This allows for customization to meet unique testing needs.
- Language Support: Selenium supports a wide range of programming languages, providing flexibility in its usage.
- Ease of Installation: Installing Selenium is straightforward—it primarily involves adding a browser extension.
Extensive Documentation: Comprehensive documentation is available, simplifying understanding and usage of Selenium.

### Disadvantages:

- Limited to Web Applications: As Selenium is a browser plugin, it cannot test desktop applications.
- Additional Plugins Required: To identify GUI components, you often need to install additional browser plugins.
- Website Limitations: Selenium might not support automation on certain websites.
- Manual Adjustment of Execution Speed: Selenium tests may require manual adjustments to the timing and speed of execution to handle dynamic content and ensure that elements are fully loaded and interactive before they're interacted with. This can be tricky, as the optimal wait time can vary widely based on factors like network latency, server response time, client-side rendering time, etc. This means that test scripts might need to be adjusted on a case-by-case basis, which can increase the complexity and maintenance overhead of the test suite.
  
## Sikulix

### Advantages:

- Image Recognition: Sikulix uses image recognition powered by OpenCV to identify GUI components. This feature is beneficial when GUI internals or source code is inaccessible.
- Text Recognition: Thanks to Tesseract, Sikulix has basic text recognition capabilities, which enables it to search for text in images.
- Useful for Development Testing: Sikulix can test applications or web apps that are under development.
- Repetitive Task Automation: Sikulix can automate monotonous tasks.
  
### Disadvantages:

- Screen Resolution Dependency: Since Sikulix uses OpenCV for image recognition, test cases are dependent on the screen resolution at which they were recorded. As a result, they may not work on screens with different resolutions.
- Platform-Dependent Scripts: Scripts in Sikulix are platform-dependent, which can limit their portability.
- Error Handling: Handling runtime errors can be challenging, especially during long script runs.
- Less Community Support: Sikulix is less popular than Selenium, which results in reduced community support.
- Poor Documentation: Sikulix's documentation is not as comprehensive as Selenium's, which could hinder its usage and understanding.

# How the team work/effort was divided and managed

GUI Testing    
We divided this task as instructed in the assignment. Each team member was responsible for writing two Selenium test cases.

| Test cases            | Taken by: |
|-----------------------|-----------|
|     Customer Reviews             |  Jason   |
|         Search bar         |   Jason   |
|    Shop by Room   |  Steven   |
| Shop by Department |  Steven    |
|    Warehouse Value & Specials   |   Chris  |
|     Sign In          |  Chris     |
|   Add Item to Cart   |    Jash   |
|      Change Store        |  Jash   |
|      Find Home Service       |  Nikhil   |
|      Search in Weekly Flyer         |   Nikhil  |

The mutation testing was split as pre the previous assignemnts with each member reasonsible for two methods.  If the methods mutation score couldn't be increased then they were reasonsible for adding test cases to their class for methods not previously tested.  See below for the breakdown.

Class | Method | Tester
--- | --- | ---
Range | getLowerBound() | Christopher DiMattia 
Range | getUpperBound() | Christopher DiMattia 
Range | toString() | Steven Duong
Range | getLength() | Steven Duong
Range| combine(Range range1, Range range2) | Nikhil Naikar
DataUtilities | createNumberArray2D(double[][] data) | Nikhil Naikar
DataUtilities | calculateRowTotal(Values2D data, int row)  | Jason Xu
DataUtilities | createNumberArray(double[] data) | Jason Xu
DataUtilities | calculateColumnTotal(Values2D data, int column) | Jash Dubal
DataUtilities| getCumulativePercentages(KeyedValues data) | Jash Dubal

# Difficulties encountered, challenges overcome, and lessons learned

Difficulties and Challenges

1. One challenge was handling iframes and pop-up windows, as Selenium can interact only with the currently focused window or frame. We had to learn how to correctly switch focus to handle these situations.
2. A notable challenge encountered while employing the PIT tool was the lack of clarity in some of the explanations provided for the injected mutants during the analysis process.
3. Researching how to deal with equivalent mutations took more time than expected and answers were not clear as there doesn't appear to be a clearly defined way to deal with them and many different solutions seems plausible.

# Comments/feedback on the lab itself

1. The lab was a valuable and practical learning experience that allowed us to apply theoretical knowledge of Selenium in a real-world scenario. The practical exposure to automated testing using Selenium was extremely beneficial, providing firsthand experience in handling various web components and situations.
2. The utilization of the PIT software proved highly beneficial in evaluating the effectiveness of our test cases. The complete procedure, encompassing the execution of the PIT tool, examination of the mutants to determine their survival or if they were killed, incorporation of additional test cases to eliminate surviving mutants, and thoughtful analysis of equivalent mutants, provided a valuable learning opportunity.
3. Many of the team members struggled to get the PIT tool working and had to rely on working with other members to perform their testing.
