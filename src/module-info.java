/**
 * 
 */
/**
 * 
 */
module eventos {
	exports com.pratique.domain.usuarios to com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
	exports com.pratique.domain.eventos to com.fasterxml.jackson.databind;
}