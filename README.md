**Running the Program and Navigating the Application**
**Prerequisites:**
- Ensure Java is installed on your machine.
- Have Gradle set up for build automation.


**Steps to Run:**
- Open the terminal or command prompt and navigate to the project directory.
- Execute the command ‘gradle run’ to start the application.
- Once the application starts, you'll be greeted with the **Main Menu**.


**Navigating the Main Menu:**
- Option 1: Display Menu (Dish Menu)
    - Presents a list of available dishes with unique item number.
    - Users can order dishes by inputting the corresponding item number. If the dish has been previously added, the quantity is incremented.
    - To finalize your selection and return to the main menu, press 0.

- Option 2: View Orders (Shopping Cart)
    - Displays currently ordered dishes with quantities and total costs.
    - Provides the option to:
        - Return to the main menu and order more dishes.
        - Remove a specific dish by inputting its number.

- Option 3: Process Payment
    - If the order list is empty, the user will be prompted to add dishes.
    - For non-empty orders, the total cost and order details will be displayed.
    - Before proceeding to payment, users will be prompted to select their preferred delivery method:
        - Delivery
        - Pickup
    - After choosing the delivery method, the user will be presented with the following options:
        - Pay for the order, upon which an order number is generated.
        - Return to the main menu without processing the payment.

- Option 4: View Order History
    - Displays a list of past orders with unique order numbers, payment dates, total amounts, and the number of dishes in each order.
    - Users can input an order number to view detailed information about that specific order.
    - Press 0 to return to the main menu.

- Option 5: Exit
    - Selecting this option will safely terminate the application.
    - Any unsaved changes or unpaid orders will not be stored, so it's essential to process payments and finalize any other operations before choosing to exit.
    - After selecting this option, you'll be returned to the terminal or command prompt.
 
- Option 6: Admin Login(Associate with **Admin Dashboard**)
    - Allow the administrator to input a username and password for login validation.
    - If the validation fails, display a prompt message and automatically return to the main menu.


- Optin 6*: Exit Admin Mode(After successful login validation）
    - Allow exiting the administrator mode, returning to the main menu, and changing back to the 'Admin Login' option.

- Option 7: Admin Control Panel
    - Display the "Admin dashboard".


**Admin Dashboard**
- Option 1: Add Item
    - Enter the product ID, name, description, and price to add a product to the menu.
    - Do not input an existing product ID or it will return a prompt message.

- Option 2: Delete Item
    - Enter the product ID to remove a product from the menu.
    - Do not input a non-existent product ID or it will return a prompt message.
 
 - Option 3: Modify Item
    - Enter the product ID to modify the existing product's name, description, and price on the menu.
    - Do not input a non-existent product ID or it will return a prompt message.
 
 - Option 4: Register Administrator Account
    - Enter a username starting with a letter and a password of at least 8 characters to create a new administrator account.
    - The username and password must not contain spaces.
 
 - Option 5: Account the total number of order
    - Display the total number of processed historical orders.
 
 - Option 6: Return to Main Menu
    - Return to the main menu to allow administrators to perform all the operations available to regular users.


- **Note on Order Numbers**: Every order is associated with a unique order number, which acts as a reference for users to view their order history. The system ensures order numbers are incrementally generated and consistent across multiple program runs.
