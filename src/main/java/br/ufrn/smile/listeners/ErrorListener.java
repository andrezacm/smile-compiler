package br.ufrn.smile.listeners;

import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import br.ufrn.smile.SmileParser;
import br.ufrn.smile.domain.CustomError;
import br.ufrn.smile.service.ErrorHandler;

public class ErrorListener extends BaseErrorListener {
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
		List<String> stack = ((SmileParser)recognizer).getRuleInvocationStack();
		Collections.reverse(stack);
		
		String message = "error on " + stack + 
						 " line "+ line + 
						 ":" + charPositionInLine + 
						 " at " + offendingSymbol + 
						 ": " + msg;
		
		ErrorHandler.getErrorHandler().addError(CustomError.CustomErrorFromMessage(message));
	}
}
