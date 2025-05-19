/**
 * 
 */
/**
 * 
 */
module NoteApp {
	requires org.junit.jupiter.api;
	requires java.desktop;
	requires java.sql;
	requires org.json;
	opens com.unanoteapp.repository to org.junit.platform.commons;
    exports com.unanoteapp.model;
    exports com.unanoteapp.repository;


}