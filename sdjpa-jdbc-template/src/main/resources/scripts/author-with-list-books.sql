
SELECT author.id as id, first_name, last_name, book.id as book_id, book.isbn, book.publisher, book.title FROM author
LEFT OUTER JOIN book ON author.id = book.author_id WHERE author.id = 1;


