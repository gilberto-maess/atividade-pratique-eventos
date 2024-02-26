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
	requires com.fasterxml.jackson.datatype.jsr310;
	requires com.fasterxml.jackson.annotation;

	exports com.pratique.domain.eventos to com.fasterxml.jackson.databind;

	opens com.pratique.domain.eventos to com.fasterxml.jackson.databind;

	exports com.pratique.domain.enderecos to com.fasterxml.jackson.databind;
}