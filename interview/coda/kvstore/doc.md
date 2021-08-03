> COMMIT and ROLLBACK are the two terms 
> used in the transactional statement to perform or undo the SQL transaction


- A COMMIT is the SQL command used in the transaction tables or database 
  to make the current transaction or database statement as permanent
- ROLLBACK command is used to roll back the current transaction state 
  if any error occurred during the execution of a transaction 
- A rollback command can only be executed if 
  the user has not performed the COMMIT command on the current transaction or statement
  

In InnoDB, you do not need to explicitly start or end transactions for single queries 
if you have not changed the default setting of autocommit, which is "on". 
If autocommit is on, InnoDB automatically encloses every single SQL query in a transaction, 
which is the equivalent of START TRANSACTION; query; COMMIT;


If you explicitly use START TRANSACTION in InnoDB with autocommit on, 
then any queries executed after a START TRANSACTION statement will either all be executed, 
or all of them will fail.