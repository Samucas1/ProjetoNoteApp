/**
 * 
 */
/**
 * 
 */
module NoteApp {
	requires org.junit.jupiter.api;
	requires java.sql;
	opens com.unanoteapp.repository to org.junit.platform.commons;
    exports com.unanoteapp.model;
    exports com.unanoteapp.repository;
}