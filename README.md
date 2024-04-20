# Inventory Management System

## Description

This is an Inventory Management System. It holds the list of Parts, Products and the associated parts with each product. Users can enter the data using forms for both parts and products. Further, users have the option to store the data into the database or a file(.dat). Lastly, the user can load the data from the database or a file(.dat).

## Tech Stack

<img src="https://skillicons.dev/icons?i=java,sqlite,eclipse" />

## How it looks

<a href="https://github.com/busycaesar/Inventory_Management_System/blob/Master/ApplicationLooks.md">Application Looks</a>

## Features

### Add new part

Users can add a new part which can either be manufactured inhouse or outsourced. Accordingly the form fields will be changed to store the data related to a part. The 'Add Part' form has the following security features before storing any new part data:

- Makes sure that every fields are filled.
- The units available and within max allowed and min required.
- The data received is/can be converted into the expected type.

In case of any of the above error, a proper message is displayed.

### Add new product

Users can add a new product which has atmost one associated part with it. The 'Add Product' form has the following security features before storing any new part data:

- Makes sure that every fields are filled.
- There is atmost one part associated with it.
- The cost of the product should be more than the total cost of all the associated parts.
- The units available and within max allowed and min required.
- The data received is/can be converted into the expected type.

In case of any of the above error, a proper message is displayed.

### Search part/product by id or name:

Any stored part/product can be searched by entering its id or name in the search box on the main menu. While searching by name, it also tries to match if the substring enter belongs to the name of any of the part/product.

### Update any part/product:

Any part/product can be updated by selecting the particular part/product from the table and clicking the 'Update' button. It leads the user to the corresponding form having the existing details of part/product which can later be updated.

### Delete any part/product:

Any part/product can be deleted by selecting the particular part/product from the table and clicking the 'Delete' button. The application prompts the user once before actually deleting the part/product to make sure the user did not click the 'Delete' button by accident.

### Working with database:

- **Store the data**: The parts and products data created can be stored into a database by clicking the 'Save to database' button. The appliction drops all the existing parts and products data as well as tables before storing the new data.

- **Load the data**: The data can be loaded from the database into the tables by clicking the 'Load data from DB' button. The application loads the data from the tables into the object and refreshes both the tables.

### Working with file(.dat):

- **Store the data**: The parts and products data created can be stored into a file by clicking the 'Save to file' button. The appliction uses writeObject to store the whole object carrying the data into the file.

- **Load the data**: The data can be loaded from the file into the tables by clicking the 'Load data from file' button. The application loads the data using readObject from the file into the object and refreshes both the tables.
