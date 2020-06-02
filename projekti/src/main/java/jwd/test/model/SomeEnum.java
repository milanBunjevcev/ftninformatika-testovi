package jwd.test.model;

import java.util.Optional;

public enum SomeEnum {

	A, B, C;

	public Optional<SomeEnum> next() {
		switch (this) {
		case A:
			return Optional.of(B);
		case B:
			return Optional.of(C);
		default:
			return Optional.empty();
		}
	}

}
