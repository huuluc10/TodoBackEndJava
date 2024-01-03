create database Todo;

use Todo;

CREATE TABLE
    Role (
        roleId int not null primary key,
        role varchar(10) not null
    );

create table
    User (
        userId bigint not null primary key,
        username varchar(255) null,
        roleId int not null,
        password varchar(255) null,
        Foreign Key (roleId) REFERENCES Role(roleId)
    );

create table
    Todo (
        todoId bigint not null primary key,
        content varchar(1000) null,
        isComplete tinyint(1) null
    );

create table
    UserTodo (
        userId bigint not null,
        todoId bigint not null,
        primary key (userId, todoId),
        foreign key (userId) references User(userId),
        foreign key (todoId) references Todo(todoId)
    );

DELIMITER $$

CREATE PROCEDURE DELETE_USER(IN USERID BIGINT)
BEGIN
	DECLARE affected_rows INT DEFAULT 0;
	DECLARE result INT DEFAULT -1;
	START TRANSACTION;
	DELETE FROM UserTodo WHERE UserTodo.userId = userId;
	SET affected_rows = affected_rows + ROW_COUNT();
	DELETE FROM User WHERE User.userId = userId;
	SET affected_rows = affected_rows + ROW_COUNT();
	DELETE FROM Todo
	WHERE todoId NOT IN (
	        SELECT todoId
	        FROM UserTodo
	    );
	SET affected_rows = affected_rows + ROW_COUNT();
	IF affected_rows > 2 THEN SET result = 1;
	ELSE SET result = -1;
	END IF;
	COMMIT;
	SELECT result;
	END $$


DELIMITER;

DELIMITER $$

CREATE PROCEDURE DELETE_TODO(IN USERID BIGINT, IN TODOID BIGINT)
BEGIN
	DECLARE affected_rows INT DEFAULT 0;
	DECLARE result INT DEFAULT -1;
	START TRANSACTION;
	DELETE FROM UserTodo
	WHERE
	    UserTodo.todoId = todoId
	    AND UserTodo.userId = userId;
	SET affected_rows = affected_rows + ROW_COUNT();
	DELETE FROM Todo WHERE Todo.todoId = todoId;
	SET affected_rows = affected_rows + ROW_COUNT();
	IF affected_rows > 1 THEN SET result = 1;
	ELSE SET result = -1;
	END IF;
	COMMIT;
	SELECT result;
	END $$


DELIMITER;

DELIMITER $$

CREATE PROCEDURE INSERT_TODO(IN USERID BIGINT, IN TODOID BIGINT, IN CONTENT VARCHAR(1000), IN ISCOMPLETE TINYINT)
BEGIN
	DECLARE affected_rows INT DEFAULT 0;
	DECLARE exit handler for sqlexception BEGIN SET result = -1;
	END;
	START TRANSACTION;
	INSERT INTO
	    Todo (
	        Todo.todoId,
	        Todo.content,
	        Todo.isComplete
	    )
	VALUES (todoId, content, isComplete);
	SET affected_rows = affected_rows + ROW_COUNT();
	INSERT INTO
	    UserTodo (
	        UserTodo.userId,
	        UserTodo.todoID
	    )
	VALUES (userId, todoId);
	SET affected_rows = affected_rows + ROW_COUNT();
	IF affected_rows > 1 THEN SET result = 1;
	ELSE SET result = -1;
	END IF;
	COMMIT;
	END $$


DELIMITER;

INSERT INTO Role (roleId, role) values (1, 'ADMIN');

INSERT INTO Role (roleId, role) values (2, 'USER');

INSERT INTO
    Todo.User (userId, username, password)
VALUES (
        0,
        'admin',
        1,
        '$2a$10$jKcUA4Jv9nKtZ7TdcRl.s.j7t0Z.aXCpfv0bOcy03N1SeyLt01An6' //admin
    );

INSERT INTO
    Todo.User (userId, username, password)
VALUES (
        1,
        'lucnh',
        2,
        '$2a$10$D8SFFDi3HMpHETtHoqfx.Ox6cnnG4HBeNFjif3BbzcAqeus9M0TUS'  //123
    );

INSERT INTO
    Todo.User (userId, username, password)
VALUES (
        2,
        'khangvt',
        2,
        '$2a$10$D8SFFDi3HMpHETtHoqfx.Ox6cnnG4HBeNFjif3BbzcAqeus9M0TUS'  //123
    );

INSERT INTO
    Todo.Todo (todoId, content, isComplete)
VALUES (1, 'Todo 1', 0);

INSERT INTO
    Todo.Todo (todoId, content, isComplete)
VALUES (2, 'Todo 2', 1);

INSERT INTO
    Todo.Todo (todoId, content, isComplete)
VALUES (3, 'Todo 3', 1);

INSERT INTO
    Todo.Todo (todoId, content, isComplete)
VALUES (4, 'Todo 4', 0);

INSERT INTO
    Todo.Todo (todoId, content, isComplete)
VALUES (5, 'Todo 5', 0);

INSERT INTO
    Todo.Todo (todoId, content, isComplete)
VALUES (6, 'Todo 6', 1);

INSERT INTO
    Todo.Todo (todoId, content, isComplete)
VALUES (7, 'Todo 7', 0);

INSERT INTO Todo.UserTodo (userId, todoId) VALUES (1, 1);

INSERT INTO Todo.UserTodo (userId, todoId) VALUES (2, 2);

INSERT INTO Todo.UserTodo (userId, todoId) VALUES (1, 3);

INSERT INTO Todo.UserTodo (userId, todoId) VALUES (2, 4);

INSERT INTO Todo.UserTodo (userId, todoId) VALUES (2, 5);

INSERT INTO Todo.UserTodo (userId, todoId) VALUES (1, 6);

INSERT INTO Todo.UserTodo (userId, todoId) VALUES (1, 7);