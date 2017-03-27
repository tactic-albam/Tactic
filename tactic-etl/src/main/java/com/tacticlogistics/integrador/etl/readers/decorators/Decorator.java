package com.tacticlogistics.integrador.etl.readers.decorators;

import java.nio.file.Path;

public interface Decorator<I, O> {

	public O transform(I input);
}

class Reader implements Decorator<Path,String>{

	@Override
	public String transform(Path input) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
