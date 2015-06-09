package com.sogou.map.logreplay.dao.base;


import java.util.Arrays;

public enum QueryOperator {

	eq {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return equal_to.buildResult(fieldName, originKey);
		}
	},
	equal_to {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return new OperationParsedResult(fieldName, " = ", ":" + originKey, originKey);
		}
	},
	ne {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return not_equal_to.buildResult(fieldName, originKey);
		}
	},
	not_equal_to {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return new OperationParsedResult(fieldName, " != ", ":" + originKey, originKey);
		}
	},
	gt {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return greater_than.buildResult(fieldName, originKey);
		}
	},
	greater_than {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return new OperationParsedResult(fieldName, " > ", ":" + originKey, originKey);
		}
	},
	ge {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return greater_than_or_equal_to.buildResult(fieldName, originKey);
		}
	},
	greater_than_or_equal_to {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return new OperationParsedResult(fieldName, " >= ", ":" + originKey, originKey);
		}
	},
	lt {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return less_than.buildResult(fieldName, originKey);
		}
	},
	less_than {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return new OperationParsedResult(fieldName, " < ", ":" + originKey, originKey);
		}
	},
	le {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return less_than_or_equal_to.buildResult(fieldName, originKey);
		}
	},
	less_than_or_equal_to {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return new OperationParsedResult(fieldName, " <= ", ":" + originKey, originKey);
		}
	},
	contain {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			String placeholder = concatFunction.render(Arrays.asList("'%'", ":" + originKey, "'%'"));
			return new OperationParsedResult(fieldName, " like ", placeholder, originKey);
		}
	},
	not_contain {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			String placeholder = concatFunction.render(Arrays.asList("'%'", ":" + originKey, "'%'"));
			return new OperationParsedResult(fieldName, " not like ", placeholder, originKey);
			
		}
	},
	start_with {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			String placeholder = concatFunction.render(Arrays.asList(":" + originKey, "'%'"));
			return new OperationParsedResult(fieldName, " like ", placeholder, originKey);
		}
	},
	not_start_with {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			String placeholder = concatFunction.render(Arrays.asList(":" + originKey, "'%'"));
			return new OperationParsedResult(fieldName, " not like ", placeholder, originKey);
		}
	},
	end_with {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			String placeholder = concatFunction.render(Arrays.asList("'%'", ":" + originKey));
			return new OperationParsedResult(fieldName, " like ", placeholder, originKey);
		}
	},
	not_end_with {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			String placeholder = concatFunction.render(Arrays.asList("'%'", ":" + originKey));
			return new OperationParsedResult(fieldName, " not like ", placeholder, originKey);
		}
	},
	in {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return new OperationParsedResult(fieldName, " in ", "(:" + originKey + ")", originKey);
		}
	},
	not_in {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return new OperationParsedResult(fieldName, " not in ", "(:" + originKey + ")", originKey);
		}
	},
	is_null {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return new OperationParsedResult(fieldName, " is null ", "", originKey);
		}
	},
	is_not_null {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			return new OperationParsedResult(fieldName, " is not null ", "", originKey);
		}
	},
	exists {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			int index = originKey.lastIndexOf( __ );
			String existsClause = originKey.substring(0, index);
			return new OperationParsedResult("", " exists (" + existsClause + ") ", "", originKey);
		}
	},
	not_exists {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			int index = originKey.lastIndexOf( __ );
			String existsClause = originKey.substring(0, index);
			return new OperationParsedResult("", " not exists (" + existsClause + ")", "", originKey);
		}
	},
	sub_query {
		@Override
		public OperationParsedResult buildResult(String fieldName, String originKey) {
			int index = originKey.lastIndexOf( __ );
			String subQuery = originKey.substring(0, index);
			return new OperationParsedResult("", "(" + subQuery + ")", "", originKey);
		}
	}
	;
	
	static final String __ = "__";
	
	private static VarArgsSQLFunction concatFunction = new VarArgsSQLFunction("concat(",",", ")");
	
	public abstract OperationParsedResult buildResult(String fieldName, String originKey);
	
}