// Generated from Boa.g by ANTLR 4.5
package boa.parser;

import java.util.ArrayList;

import boa.compiler.ast.*;
import boa.compiler.ast.expressions.*;
import boa.compiler.ast.literals.*;
import boa.compiler.ast.statements.*;
import boa.compiler.ast.types.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OF=1, IF=2, DO=3, MAP=4, STACK=5, SET=6, FOR=7, FOREACH=8, IFALL=9, EXISTS=10, 
		NOT=11, TYPE=12, ELSE=13, CASE=14, OUTPUT=15, FORMAT=16, WHILE=17, BREAK=18, 
		ARRAY=19, STATIC=20, SWITCH=21, RETURN=22, WEIGHT=23, DEFAULT=24, CONTINUE=25, 
		FUNCTION=26, VISITOR=27, BEFORE=28, AFTER=29, STOP=30, ENUM=31, SEMICOLON=32, 
		COLON=33, COMMA=34, DOT=35, LBRACE=36, RBRACE=37, LPAREN=38, RPAREN=39, 
		LBRACKET=40, RBRACKET=41, OR=42, ONEOR=43, TWOOR=44, AND=45, ONEAND=46, 
		TWOAND=47, INCR=48, DECR=49, EQEQ=50, NEQ=51, LT=52, LTEQ=53, GT=54, GTEQ=55, 
		PLUS=56, MINUS=57, XOR=58, STAR=59, DIV=60, MOD=61, RSHIFT=62, NEG=63, 
		INV=64, WILDCARD=65, QUESTION=66, DOLLAR=67, EQUALS=68, EMIT=69, RIGHT_ARROW=70, 
		IntegerLiteral=71, FloatingPointLiteral=72, CharacterLiteral=73, RegexLiteral=74, 
		StringLiteral=75, TimeLiteral=76, Identifier=77, WS=78, LINE_COMMENT=79;
	public static final int
		RULE_start = 0, RULE_program = 1, RULE_programStatement = 2, RULE_declaration = 3, 
		RULE_typeDeclaration = 4, RULE_staticVariableDeclaration = 5, RULE_variableDeclaration = 6, 
		RULE_type = 7, RULE_component = 8, RULE_enumBodyDeclaration = 9, RULE_arrayType = 10, 
		RULE_tupleType = 11, RULE_enumType = 12, RULE_member = 13, RULE_mapType = 14, 
		RULE_stackType = 15, RULE_setType = 16, RULE_outputType = 17, RULE_modelType = 18, 
		RULE_functionType = 19, RULE_visitorType = 20, RULE_statement = 21, RULE_emptyStatement = 22, 
		RULE_assignmentStatement = 23, RULE_block = 24, RULE_breakStatement = 25, 
		RULE_continueStatement = 26, RULE_doStatement = 27, RULE_emitStatement = 28, 
		RULE_forStatement = 29, RULE_forExpression = 30, RULE_forVariableDeclaration = 31, 
		RULE_forExpressionStatement = 32, RULE_expressionStatement = 33, RULE_ifStatement = 34, 
		RULE_returnStatement = 35, RULE_switchStatement = 36, RULE_switchCase = 37, 
		RULE_foreachStatement = 38, RULE_existsStatement = 39, RULE_ifallStatement = 40, 
		RULE_whileStatement = 41, RULE_visitStatement = 42, RULE_stopStatement = 43, 
		RULE_expression = 44, RULE_expressionList = 45, RULE_conjunction = 46, 
		RULE_comparison = 47, RULE_simpleExpression = 48, RULE_term = 49, RULE_factor = 50, 
		RULE_selector = 51, RULE_index = 52, RULE_call = 53, RULE_operand = 54, 
		RULE_unaryFactor = 55, RULE_parenExpression = 56, RULE_functionExpression = 57, 
		RULE_visitorExpression = 58, RULE_composite = 59, RULE_pair = 60, RULE_identifier = 61, 
		RULE_integerLiteral = 62, RULE_floatingPointLiteral = 63, RULE_characterLiteral = 64, 
		RULE_stringLiteral = 65, RULE_timeLiteral = 66;
	public static final String[] ruleNames = {
		"start", "program", "programStatement", "declaration", "typeDeclaration", 
		"staticVariableDeclaration", "variableDeclaration", "type", "component", 
		"enumBodyDeclaration", "arrayType", "tupleType", "enumType", "member", 
		"mapType", "stackType", "setType", "outputType", "modelType", "functionType", 
		"visitorType", "statement", "emptyStatement", "assignmentStatement", "block", 
		"breakStatement", "continueStatement", "doStatement", "emitStatement", 
		"forStatement", "forExpression", "forVariableDeclaration", "forExpressionStatement", 
		"expressionStatement", "ifStatement", "returnStatement", "switchStatement", 
		"switchCase", "foreachStatement", "existsStatement", "ifallStatement", 
		"whileStatement", "visitStatement", "stopStatement", "expression", "expressionList", 
		"conjunction", "comparison", "simpleExpression", "term", "factor", "selector", 
		"index", "call", "operand", "unaryFactor", "parenExpression", "functionExpression", 
		"visitorExpression", "composite", "pair", "identifier", "integerLiteral", 
		"floatingPointLiteral", "characterLiteral", "stringLiteral", "timeLiteral"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'of'", "'if'", "'do'", "'map'", "'stack'", "'set'", "'for'", "'foreach'", 
		"'ifall'", "'exists'", "'not'", "'type'", "'else'", "'case'", "'output'", 
		"'format'", "'while'", "'break'", "'array'", "'static'", "'switch'", "'return'", 
		"'weight'", "'default'", "'continue'", "'function'", "'visitor'", "'before'", 
		"'after'", "'stop'", "'enum'", "';'", "':'", "','", "'.'", "'{'", "'}'", 
		"'('", "')'", "'['", "']'", "'or'", "'|'", "'||'", "'and'", "'&'", "'&&'", 
		"'++'", "'--'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'+'", "'-'", 
		"'^'", "'*'", "'/'", "'%'", "'>>'", "'~'", "'!'", "'_'", "'?'", "'$'", 
		"'='", "'<<'", "'->'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OF", "IF", "DO", "MAP", "STACK", "SET", "FOR", "FOREACH", "IFALL", 
		"EXISTS", "NOT", "TYPE", "ELSE", "CASE", "OUTPUT", "FORMAT", "WHILE", 
		"BREAK", "ARRAY", "STATIC", "SWITCH", "RETURN", "WEIGHT", "DEFAULT", "CONTINUE", 
		"FUNCTION", "VISITOR", "BEFORE", "AFTER", "STOP", "ENUM", "SEMICOLON", 
		"COLON", "COMMA", "DOT", "LBRACE", "RBRACE", "LPAREN", "RPAREN", "LBRACKET", 
		"RBRACKET", "OR", "ONEOR", "TWOOR", "AND", "ONEAND", "TWOAND", "INCR", 
		"DECR", "EQEQ", "NEQ", "LT", "LTEQ", "GT", "GTEQ", "PLUS", "MINUS", "XOR", 
		"STAR", "DIV", "MOD", "RSHIFT", "NEG", "INV", "WILDCARD", "QUESTION", 
		"DOLLAR", "EQUALS", "EMIT", "RIGHT_ARROW", "IntegerLiteral", "FloatingPointLiteral", 
		"CharacterLiteral", "RegexLiteral", "StringLiteral", "TimeLiteral", "Identifier", 
		"WS", "LINE_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Boa.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	protected int getStartLine() {
		return getCurrentToken().getLine();
	}
	protected int getStartColumn() {
		return getCurrentToken().getCharPositionInLine();
	}
	protected int getEndLine() {
		Token t = _input.LT(-1);
		if (t == null) t = getCurrentToken();
		return t.getLine();
	}
	protected int getEndColumn() {
		Token t = _input.LT(-1);
		if (t == null) t = getCurrentToken();
		return t.getCharPositionInLine() + t.getText().length() - 1;
	}
	protected void isSemicolon() {
		if (!getCurrentToken().getText().equals(";")) {
			notifyErrorListeners("error: ';' expected");
			return;
		}
		setState(getState() + 1);
		match(SEMICOLON);
	}

	public BoaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public Start ast;
		public int l;
		public int c;
		public ProgramContext p;
		public TerminalNode EOF() { return getToken(BoaParser.EOF, 0); }
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		 ((StartContext)_localctx).l =  getStartLine(); ((StartContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			((StartContext)_localctx).p = program();
			setState(135);
			match(EOF);
			 ((StartContext)_localctx).ast =  new Start(((StartContext)_localctx).p.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public Program ast;
		public int l;
		public int c;
		public ProgramStatementContext s;
		public List<ProgramStatementContext> programStatement() {
			return getRuleContexts(ProgramStatementContext.class);
		}
		public ProgramStatementContext programStatement(int i) {
			return getRuleContext(ProgramStatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_program);

				((ProgramContext)_localctx).l =  getStartLine(); ((ProgramContext)_localctx).c =  getStartColumn();
				((ProgramContext)_localctx).ast =  new Program();
			
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(138);
				((ProgramContext)_localctx).s = programStatement();
				 _localctx.ast.addStatement(((ProgramContext)_localctx).s.ast); 
				}
				}
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP) | (1L << SEMICOLON) | (1L << LBRACE) | (1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << NEG))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INV - 64)) | (1L << (DOLLAR - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (CharacterLiteral - 64)) | (1L << (RegexLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (TimeLiteral - 64)) | (1L << (Identifier - 64)))) != 0) );
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramStatementContext extends ParserRuleContext {
		public Statement ast;
		public DeclarationContext d;
		public StatementContext s;
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ProgramStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterProgramStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitProgramStatement(this);
		}
	}

	public final ProgramStatementContext programStatement() throws RecognitionException {
		ProgramStatementContext _localctx = new ProgramStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_programStatement);
		try {
			setState(151);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				((ProgramStatementContext)_localctx).d = declaration();
				 ((ProgramStatementContext)_localctx).ast =  ((ProgramStatementContext)_localctx).d.ast; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				((ProgramStatementContext)_localctx).s = statement();
				 ((ProgramStatementContext)_localctx).ast =  ((ProgramStatementContext)_localctx).s.ast; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public Statement ast;
		public TypeDeclarationContext t;
		public StaticVariableDeclarationContext s;
		public VariableDeclarationContext v;
		public TypeDeclarationContext typeDeclaration() {
			return getRuleContext(TypeDeclarationContext.class,0);
		}
		public StaticVariableDeclarationContext staticVariableDeclaration() {
			return getRuleContext(StaticVariableDeclarationContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration);
		try {
			setState(162);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				((DeclarationContext)_localctx).t = typeDeclaration();
				 ((DeclarationContext)_localctx).ast =  ((DeclarationContext)_localctx).t.ast; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				((DeclarationContext)_localctx).s = staticVariableDeclaration();
				 ((DeclarationContext)_localctx).ast =  ((DeclarationContext)_localctx).s.ast; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(159);
				((DeclarationContext)_localctx).v = variableDeclaration();
				 ((DeclarationContext)_localctx).ast =  ((DeclarationContext)_localctx).v.ast; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDeclarationContext extends ParserRuleContext {
		public TypeDecl ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public TypeContext t;
		public TerminalNode TYPE() { return getToken(BoaParser.TYPE, 0); }
		public TerminalNode EQUALS() { return getToken(BoaParser.EQUALS, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitTypeDeclaration(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_typeDeclaration);
		 ((TypeDeclarationContext)_localctx).l =  getStartLine(); ((TypeDeclarationContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(TYPE);
			setState(165);
			((TypeDeclarationContext)_localctx).id = identifier();
			setState(166);
			match(EQUALS);
			setState(167);
			((TypeDeclarationContext)_localctx).t = type();
			 isSemicolon(); ((TypeDeclarationContext)_localctx).ast =  new TypeDecl(((TypeDeclarationContext)_localctx).id.ast, ((TypeDeclarationContext)_localctx).t.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StaticVariableDeclarationContext extends ParserRuleContext {
		public VarDeclStatement ast;
		public int l;
		public int c;
		public VariableDeclarationContext v;
		public TerminalNode STATIC() { return getToken(BoaParser.STATIC, 0); }
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public StaticVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staticVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterStaticVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitStaticVariableDeclaration(this);
		}
	}

	public final StaticVariableDeclarationContext staticVariableDeclaration() throws RecognitionException {
		StaticVariableDeclarationContext _localctx = new StaticVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_staticVariableDeclaration);
		 ((StaticVariableDeclarationContext)_localctx).l =  getStartLine(); ((StaticVariableDeclarationContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(STATIC);
			setState(171);
			((StaticVariableDeclarationContext)_localctx).v = variableDeclaration();
			 ((StaticVariableDeclarationContext)_localctx).ast =  ((StaticVariableDeclarationContext)_localctx).v.ast; _localctx.ast.setStatic(true); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public VarDeclStatement ast;
		public int l;
		public int c;
		public ForVariableDeclarationContext v;
		public ForVariableDeclarationContext forVariableDeclaration() {
			return getRuleContext(ForVariableDeclarationContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitVariableDeclaration(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variableDeclaration);
		 ((VariableDeclarationContext)_localctx).l =  getStartLine(); ((VariableDeclarationContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			((VariableDeclarationContext)_localctx).v = forVariableDeclaration();
			 isSemicolon(); ((VariableDeclarationContext)_localctx).ast =  ((VariableDeclarationContext)_localctx).v.ast; 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public AbstractType ast;
		public ArrayTypeContext a;
		public MapTypeContext m;
		public TupleTypeContext t;
		public OutputTypeContext o;
		public FunctionTypeContext f;
		public VisitorTypeContext v;
		public StackTypeContext s;
		public SetTypeContext set;
		public EnumTypeContext e;
		public ModelTypeContext model;
		public IdentifierContext id;
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public MapTypeContext mapType() {
			return getRuleContext(MapTypeContext.class,0);
		}
		public TupleTypeContext tupleType() {
			return getRuleContext(TupleTypeContext.class,0);
		}
		public OutputTypeContext outputType() {
			return getRuleContext(OutputTypeContext.class,0);
		}
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public VisitorTypeContext visitorType() {
			return getRuleContext(VisitorTypeContext.class,0);
		}
		public StackTypeContext stackType() {
			return getRuleContext(StackTypeContext.class,0);
		}
		public SetTypeContext setType() {
			return getRuleContext(SetTypeContext.class,0);
		}
		public EnumTypeContext enumType() {
			return getRuleContext(EnumTypeContext.class,0);
		}
		public ModelTypeContext modelType() {
			return getRuleContext(ModelTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			setState(210);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				((TypeContext)_localctx).a = arrayType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).a.ast; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				((TypeContext)_localctx).m = mapType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).m.ast; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(183);
				((TypeContext)_localctx).t = tupleType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).t.ast; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(186);
				((TypeContext)_localctx).o = outputType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).o.ast; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(189);
				((TypeContext)_localctx).f = functionType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).f.ast; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(192);
				((TypeContext)_localctx).v = visitorType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).v.ast; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(195);
				((TypeContext)_localctx).s = stackType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).s.ast; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(198);
				((TypeContext)_localctx).set = setType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).set.ast; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(201);
				((TypeContext)_localctx).e = enumType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).e.ast; 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(204);
				((TypeContext)_localctx).model = modelType();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).model.ast; 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(207);
				((TypeContext)_localctx).id = identifier();
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).id.ast; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComponentContext extends ParserRuleContext {
		public Component ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public TypeContext t;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ComponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_component; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterComponent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitComponent(this);
		}
	}

	public final ComponentContext component() throws RecognitionException {
		ComponentContext _localctx = new ComponentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_component);

				((ComponentContext)_localctx).l =  getStartLine(); ((ComponentContext)_localctx).c =  getStartColumn();
				((ComponentContext)_localctx).ast =  new Component();
			
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(212);
				((ComponentContext)_localctx).id = identifier();
				setState(213);
				match(COLON);
				 _localctx.ast.setIdentifier(((ComponentContext)_localctx).id.ast); 
				}
				break;
			}
			setState(218);
			((ComponentContext)_localctx).t = type();
			 _localctx.ast.setType(((ComponentContext)_localctx).t.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumBodyDeclarationContext extends ParserRuleContext {
		public EnumBodyDeclaration ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public ExpressionContext e;
		public TerminalNode EQUALS() { return getToken(BoaParser.EQUALS, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public EnumBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterEnumBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitEnumBodyDeclaration(this);
		}
	}

	public final EnumBodyDeclarationContext enumBodyDeclaration() throws RecognitionException {
		EnumBodyDeclarationContext _localctx = new EnumBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_enumBodyDeclaration);

				((EnumBodyDeclarationContext)_localctx).l =  getStartLine(); ((EnumBodyDeclarationContext)_localctx).c =  getStartColumn();
			
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			((EnumBodyDeclarationContext)_localctx).id = identifier();
			setState(222);
			match(EQUALS);
			setState(223);
			((EnumBodyDeclarationContext)_localctx).e = expression();
			 ((EnumBodyDeclarationContext)_localctx).ast =  new EnumBodyDeclaration(((EnumBodyDeclarationContext)_localctx).id.ast, ((EnumBodyDeclarationContext)_localctx).e.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public ArrayType ast;
		public int l;
		public int c;
		public ComponentContext m;
		public TerminalNode ARRAY() { return getToken(BoaParser.ARRAY, 0); }
		public TerminalNode OF() { return getToken(BoaParser.OF, 0); }
		public ComponentContext component() {
			return getRuleContext(ComponentContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitArrayType(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_arrayType);
		 ((ArrayTypeContext)_localctx).l =  getStartLine(); ((ArrayTypeContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(ARRAY);
			setState(227);
			match(OF);
			setState(228);
			((ArrayTypeContext)_localctx).m = component();
			 ((ArrayTypeContext)_localctx).ast =  new ArrayType(((ArrayTypeContext)_localctx).m.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TupleTypeContext extends ParserRuleContext {
		public TupleType ast;
		public int l;
		public int c;
		public MemberContext m;
		public TerminalNode LBRACE() { return getToken(BoaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(BoaParser.RBRACE, 0); }
		public List<MemberContext> member() {
			return getRuleContexts(MemberContext.class);
		}
		public MemberContext member(int i) {
			return getRuleContext(MemberContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BoaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BoaParser.COMMA, i);
		}
		public TupleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterTupleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitTupleType(this);
		}
	}

	public final TupleTypeContext tupleType() throws RecognitionException {
		TupleTypeContext _localctx = new TupleTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_tupleType);

				((TupleTypeContext)_localctx).l =  getStartLine(); ((TupleTypeContext)_localctx).c =  getStartColumn();
				((TupleTypeContext)_localctx).ast =  new TupleType();
			
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(LBRACE);
			setState(246);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP) | (1L << ENUM) | (1L << LBRACE))) != 0) || _la==Identifier) {
				{
				setState(232);
				((TupleTypeContext)_localctx).m = member();
				 _localctx.ast.addMember(((TupleTypeContext)_localctx).m.ast); 
				setState(240);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(234);
						match(COMMA);
						setState(235);
						((TupleTypeContext)_localctx).m = member();
						 _localctx.ast.addMember(((TupleTypeContext)_localctx).m.ast); 
						}
						} 
					}
					setState(242);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				setState(244);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(243);
					match(COMMA);
					}
				}

				}
			}

			setState(248);
			match(RBRACE);
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumTypeContext extends ParserRuleContext {
		public EnumType ast;
		public int l;
		public int c;
		public EnumBodyDeclarationContext m;
		public TerminalNode ENUM() { return getToken(BoaParser.ENUM, 0); }
		public TerminalNode LBRACE() { return getToken(BoaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(BoaParser.RBRACE, 0); }
		public List<EnumBodyDeclarationContext> enumBodyDeclaration() {
			return getRuleContexts(EnumBodyDeclarationContext.class);
		}
		public EnumBodyDeclarationContext enumBodyDeclaration(int i) {
			return getRuleContext(EnumBodyDeclarationContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BoaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BoaParser.COMMA, i);
		}
		public EnumTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterEnumType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitEnumType(this);
		}
	}

	public final EnumTypeContext enumType() throws RecognitionException {
		EnumTypeContext _localctx = new EnumTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_enumType);

				((EnumTypeContext)_localctx).l =  getStartLine(); ((EnumTypeContext)_localctx).c =  getStartColumn();
				((EnumTypeContext)_localctx).ast =  new EnumType();
			
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(ENUM);
			setState(251);
			match(LBRACE);
			setState(266);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP))) != 0) || _la==Identifier) {
				{
				setState(252);
				((EnumTypeContext)_localctx).m = enumBodyDeclaration();
				 _localctx.ast.addMember(((EnumTypeContext)_localctx).m.ast); 
				setState(260);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(254);
						match(COMMA);
						setState(255);
						((EnumTypeContext)_localctx).m = enumBodyDeclaration();
						 _localctx.ast.addMember(((EnumTypeContext)_localctx).m.ast); 
						}
						} 
					}
					setState(262);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
				setState(264);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(263);
					match(COMMA);
					}
				}

				}
			}

			setState(268);
			match(RBRACE);
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberContext extends ParserRuleContext {
		public Component ast;
		public TypeDeclarationContext t;
		public StaticVariableDeclarationContext s;
		public ComponentContext c;
		public TypeDeclarationContext typeDeclaration() {
			return getRuleContext(TypeDeclarationContext.class,0);
		}
		public StaticVariableDeclarationContext staticVariableDeclaration() {
			return getRuleContext(StaticVariableDeclarationContext.class,0);
		}
		public ComponentContext component() {
			return getRuleContext(ComponentContext.class,0);
		}
		public MemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitMember(this);
		}
	}

	public final MemberContext member() throws RecognitionException {
		MemberContext _localctx = new MemberContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_member);
		try {
			setState(275);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				((MemberContext)_localctx).t = typeDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				((MemberContext)_localctx).s = staticVariableDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(272);
				((MemberContext)_localctx).c = component();
				 ((MemberContext)_localctx).ast =  ((MemberContext)_localctx).c.ast; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapTypeContext extends ParserRuleContext {
		public MapType ast;
		public int l;
		public int c;
		public ComponentContext key;
		public ComponentContext value;
		public TerminalNode MAP() { return getToken(BoaParser.MAP, 0); }
		public TerminalNode LBRACKET() { return getToken(BoaParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(BoaParser.RBRACKET, 0); }
		public TerminalNode OF() { return getToken(BoaParser.OF, 0); }
		public List<ComponentContext> component() {
			return getRuleContexts(ComponentContext.class);
		}
		public ComponentContext component(int i) {
			return getRuleContext(ComponentContext.class,i);
		}
		public MapTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterMapType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitMapType(this);
		}
	}

	public final MapTypeContext mapType() throws RecognitionException {
		MapTypeContext _localctx = new MapTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_mapType);
		 ((MapTypeContext)_localctx).l =  getStartLine(); ((MapTypeContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(MAP);
			setState(278);
			match(LBRACKET);
			setState(279);
			((MapTypeContext)_localctx).key = component();
			setState(280);
			match(RBRACKET);
			setState(281);
			match(OF);
			setState(282);
			((MapTypeContext)_localctx).value = component();
			 ((MapTypeContext)_localctx).ast =  new MapType(((MapTypeContext)_localctx).key.ast, ((MapTypeContext)_localctx).value.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StackTypeContext extends ParserRuleContext {
		public StackType ast;
		public int l;
		public int c;
		public ComponentContext m;
		public TerminalNode STACK() { return getToken(BoaParser.STACK, 0); }
		public TerminalNode OF() { return getToken(BoaParser.OF, 0); }
		public ComponentContext component() {
			return getRuleContext(ComponentContext.class,0);
		}
		public StackTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stackType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterStackType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitStackType(this);
		}
	}

	public final StackTypeContext stackType() throws RecognitionException {
		StackTypeContext _localctx = new StackTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_stackType);
		 ((StackTypeContext)_localctx).l =  getStartLine(); ((StackTypeContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(STACK);
			setState(286);
			match(OF);
			setState(287);
			((StackTypeContext)_localctx).m = component();
			 ((StackTypeContext)_localctx).ast =  new StackType(((StackTypeContext)_localctx).m.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetTypeContext extends ParserRuleContext {
		public SetType ast;
		public int l;
		public int c;
		public ComponentContext m;
		public TerminalNode SET() { return getToken(BoaParser.SET, 0); }
		public TerminalNode OF() { return getToken(BoaParser.OF, 0); }
		public ComponentContext component() {
			return getRuleContext(ComponentContext.class,0);
		}
		public SetTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterSetType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitSetType(this);
		}
	}

	public final SetTypeContext setType() throws RecognitionException {
		SetTypeContext _localctx = new SetTypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_setType);
		 ((SetTypeContext)_localctx).l =  getStartLine(); ((SetTypeContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(SET);
			setState(291);
			match(OF);
			setState(292);
			((SetTypeContext)_localctx).m = component();
			 ((SetTypeContext)_localctx).ast =  new SetType(((SetTypeContext)_localctx).m.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OutputTypeContext extends ParserRuleContext {
		public OutputType ast;
		public int l;
		public int c;
		public Token tk;
		public IdentifierContext id;
		public ExpressionListContext el;
		public ComponentContext m;
		public TerminalNode OUTPUT() { return getToken(BoaParser.OUTPUT, 0); }
		public TerminalNode OF() { return getToken(BoaParser.OF, 0); }
		public List<ComponentContext> component() {
			return getRuleContexts(ComponentContext.class);
		}
		public ComponentContext component(int i) {
			return getRuleContext(ComponentContext.class,i);
		}
		public TerminalNode SET() { return getToken(BoaParser.SET, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<TerminalNode> LPAREN() { return getTokens(BoaParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(BoaParser.LPAREN, i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(BoaParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(BoaParser.RPAREN, i);
		}
		public List<TerminalNode> LBRACKET() { return getTokens(BoaParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(BoaParser.LBRACKET, i);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(BoaParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(BoaParser.RBRACKET, i);
		}
		public TerminalNode WEIGHT() { return getToken(BoaParser.WEIGHT, 0); }
		public TerminalNode FORMAT() { return getToken(BoaParser.FORMAT, 0); }
		public List<ExpressionListContext> expressionList() {
			return getRuleContexts(ExpressionListContext.class);
		}
		public ExpressionListContext expressionList(int i) {
			return getRuleContext(ExpressionListContext.class,i);
		}
		public OutputTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outputType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterOutputType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitOutputType(this);
		}
	}

	public final OutputTypeContext outputType() throws RecognitionException {
		OutputTypeContext _localctx = new OutputTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_outputType);
		 ((OutputTypeContext)_localctx).l =  getStartLine(); ((OutputTypeContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(OUTPUT);
			setState(301);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(296);
				((OutputTypeContext)_localctx).tk = match(SET);
				 ((OutputTypeContext)_localctx).ast =  new OutputType(new Identifier((((OutputTypeContext)_localctx).tk!=null?((OutputTypeContext)_localctx).tk.getText():null))); 
				}
				break;
			case 2:
				{
				setState(298);
				((OutputTypeContext)_localctx).id = identifier();
				 ((OutputTypeContext)_localctx).ast =  new OutputType(((OutputTypeContext)_localctx).id.ast); 
				}
				break;
			}
			setState(308);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(303);
				match(LPAREN);
				setState(304);
				((OutputTypeContext)_localctx).el = expressionList();
				setState(305);
				match(RPAREN);
				 _localctx.ast.setArgs(((OutputTypeContext)_localctx).el.list); 
				}
			}

			setState(317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACKET) {
				{
				{
				setState(310);
				match(LBRACKET);
				setState(311);
				((OutputTypeContext)_localctx).m = component();
				setState(312);
				match(RBRACKET);
				 _localctx.ast.addIndice(((OutputTypeContext)_localctx).m.ast); 
				}
				}
				setState(319);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(320);
			match(OF);
			setState(321);
			((OutputTypeContext)_localctx).m = component();
			 _localctx.ast.setType(((OutputTypeContext)_localctx).m.ast); 
			setState(327);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(323);
				match(WEIGHT);
				setState(324);
				((OutputTypeContext)_localctx).m = component();
				 _localctx.ast.setWeight(((OutputTypeContext)_localctx).m.ast); 
				}
				break;
			}
			setState(334);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(329);
				match(FORMAT);
				setState(330);
				match(LPAREN);
				setState(331);
				((OutputTypeContext)_localctx).el = expressionList();
				setState(332);
				match(RPAREN);
				}
				break;
			}
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModelTypeContext extends ParserRuleContext {
		public ModelType ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public ComponentContext m;
		public TerminalNode OF() { return getToken(BoaParser.OF, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ComponentContext component() {
			return getRuleContext(ComponentContext.class,0);
		}
		public ModelTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modelType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterModelType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitModelType(this);
		}
	}

	public final ModelTypeContext modelType() throws RecognitionException {
		ModelTypeContext _localctx = new ModelTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_modelType);
		 ((ModelTypeContext)_localctx).l =  getStartLine(); ((ModelTypeContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			((ModelTypeContext)_localctx).id = identifier();
			 ((ModelTypeContext)_localctx).ast =  new ModelType(((ModelTypeContext)_localctx).id.ast); 
			setState(338);
			match(OF);
			setState(339);
			((ModelTypeContext)_localctx).m = component();
			 _localctx.ast.setType(((ModelTypeContext)_localctx).m.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionTypeContext extends ParserRuleContext {
		public FunctionType ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public TypeContext t;
		public TerminalNode FUNCTION() { return getToken(BoaParser.FUNCTION, 0); }
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public List<TerminalNode> COLON() { return getTokens(BoaParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(BoaParser.COLON, i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BoaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BoaParser.COMMA, i);
		}
		public FunctionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterFunctionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitFunctionType(this);
		}
	}

	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_functionType);

				((FunctionTypeContext)_localctx).l =  getStartLine(); ((FunctionTypeContext)_localctx).c =  getStartColumn();
				((FunctionTypeContext)_localctx).ast =  new FunctionType();
			
		int _la;
		try {
			setState(404);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				match(FUNCTION);
				setState(343);
				match(LPAREN);
				setState(359);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP))) != 0) || _la==Identifier) {
					{
					setState(344);
					((FunctionTypeContext)_localctx).id = identifier();
					setState(345);
					match(COLON);
					setState(346);
					((FunctionTypeContext)_localctx).t = type();
					 _localctx.ast.addArg(new Component(((FunctionTypeContext)_localctx).id.ast, ((FunctionTypeContext)_localctx).t.ast)); 
					setState(356);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(348);
						match(COMMA);
						setState(349);
						((FunctionTypeContext)_localctx).id = identifier();
						setState(350);
						match(COLON);
						setState(351);
						((FunctionTypeContext)_localctx).t = type();
						 _localctx.ast.addArg(new Component(((FunctionTypeContext)_localctx).id.ast, ((FunctionTypeContext)_localctx).t.ast)); 
						}
						}
						setState(358);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(361);
				match(RPAREN);
				setState(366);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(362);
					match(COLON);
					setState(363);
					((FunctionTypeContext)_localctx).t = type();
					 _localctx.ast.setType(((FunctionTypeContext)_localctx).t.ast); 
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(368);
				match(FUNCTION);
				setState(369);
				match(LPAREN);
				setState(395);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP))) != 0) || _la==Identifier) {
					{
					setState(378);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						setState(370);
						((FunctionTypeContext)_localctx).id = identifier();
						setState(371);
						match(COLON);
						setState(372);
						((FunctionTypeContext)_localctx).t = type();
						 _localctx.ast.addArg(new Component(((FunctionTypeContext)_localctx).id.ast, ((FunctionTypeContext)_localctx).t.ast)); 
						}
						break;
					case 2:
						{
						setState(375);
						identifier();
						 notifyErrorListeners("function arguments require an identifier and type"); 
						}
						break;
					}
					setState(392);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						setState(390);
						switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
						case 1:
							{
							setState(380);
							match(COMMA);
							setState(381);
							((FunctionTypeContext)_localctx).id = identifier();
							setState(382);
							match(COLON);
							setState(383);
							((FunctionTypeContext)_localctx).t = type();
							 _localctx.ast.addArg(new Component(((FunctionTypeContext)_localctx).id.ast, ((FunctionTypeContext)_localctx).t.ast)); 
							}
							break;
						case 2:
							{
							setState(386);
							match(COMMA);
							setState(387);
							identifier();
							 notifyErrorListeners("function arguments require an identifier and type"); 
							}
							break;
						}
						}
						setState(394);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(397);
				match(RPAREN);
				setState(402);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(398);
					match(COLON);
					setState(399);
					((FunctionTypeContext)_localctx).t = type();
					 _localctx.ast.setType(((FunctionTypeContext)_localctx).t.ast); 
					}
				}

				}
				break;
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VisitorTypeContext extends ParserRuleContext {
		public VisitorType ast;
		public int l;
		public int c;
		public Token t;
		public TerminalNode VISITOR() { return getToken(BoaParser.VISITOR, 0); }
		public VisitorTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_visitorType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterVisitorType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitVisitorType(this);
		}
	}

	public final VisitorTypeContext visitorType() throws RecognitionException {
		VisitorTypeContext _localctx = new VisitorTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_visitorType);
		 ((VisitorTypeContext)_localctx).l =  getStartLine(); ((VisitorTypeContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			((VisitorTypeContext)_localctx).t = match(VISITOR);
			 ((VisitorTypeContext)_localctx).ast =  new VisitorType(); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Statement ast;
		public BlockContext b;
		public AssignmentStatementContext as;
		public BreakStatementContext br;
		public ContinueStatementContext cnt;
		public StopStatementContext stp;
		public DoStatementContext ds;
		public ForStatementContext fors;
		public IfStatementContext ifs;
		public ReturnStatementContext ret;
		public SwitchStatementContext sw;
		public ForeachStatementContext each;
		public ExistsStatementContext exist;
		public IfallStatementContext all;
		public WhileStatementContext whiles;
		public EmptyStatementContext empty;
		public EmitStatementContext emit;
		public ExpressionStatementContext es;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public StopStatementContext stopStatement() {
			return getRuleContext(StopStatementContext.class,0);
		}
		public DoStatementContext doStatement() {
			return getRuleContext(DoStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public ForeachStatementContext foreachStatement() {
			return getRuleContext(ForeachStatementContext.class,0);
		}
		public ExistsStatementContext existsStatement() {
			return getRuleContext(ExistsStatementContext.class,0);
		}
		public IfallStatementContext ifallStatement() {
			return getRuleContext(IfallStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public EmitStatementContext emitStatement() {
			return getRuleContext(EmitStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_statement);
		try {
			setState(460);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(409);
				((StatementContext)_localctx).b = block();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).b.ast; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(412);
				((StatementContext)_localctx).as = assignmentStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).as.ast; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(415);
				((StatementContext)_localctx).br = breakStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).br.ast; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(418);
				((StatementContext)_localctx).cnt = continueStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).cnt.ast; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(421);
				((StatementContext)_localctx).stp = stopStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).stp.ast; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(424);
				((StatementContext)_localctx).ds = doStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).ds.ast; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(427);
				((StatementContext)_localctx).fors = forStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).fors.ast; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(430);
				((StatementContext)_localctx).ifs = ifStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).ifs.ast; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(433);
				((StatementContext)_localctx).ret = returnStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).ret.ast; 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(436);
				((StatementContext)_localctx).sw = switchStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).sw.ast; 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(439);
				((StatementContext)_localctx).each = foreachStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).each.ast; 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(442);
				((StatementContext)_localctx).exist = existsStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).exist.ast; 
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(445);
				((StatementContext)_localctx).all = ifallStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).all.ast; 
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(448);
				((StatementContext)_localctx).whiles = whileStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).whiles.ast; 
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(451);
				((StatementContext)_localctx).empty = emptyStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).empty.ast; 
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(454);
				((StatementContext)_localctx).emit = emitStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).emit.ast; 
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(457);
				((StatementContext)_localctx).es = expressionStatement();
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).es.ast; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyStatementContext extends ParserRuleContext {
		public Block ast;
		public int l;
		public int c;
		public TerminalNode SEMICOLON() { return getToken(BoaParser.SEMICOLON, 0); }
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitEmptyStatement(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_emptyStatement);
		 ((EmptyStatementContext)_localctx).l =  getStartLine(); ((EmptyStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			match(SEMICOLON);
			 ((EmptyStatementContext)_localctx).ast =  new Block(); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentStatementContext extends ParserRuleContext {
		public AssignmentStatement ast;
		public int l;
		public int c;
		public FactorContext f;
		public ExpressionContext e;
		public TerminalNode EQUALS() { return getToken(BoaParser.EQUALS, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitAssignmentStatement(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_assignmentStatement);
		 ((AssignmentStatementContext)_localctx).l =  getStartLine(); ((AssignmentStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			((AssignmentStatementContext)_localctx).f = factor();
			setState(466);
			match(EQUALS);
			setState(467);
			((AssignmentStatementContext)_localctx).e = expression();
			 isSemicolon(); ((AssignmentStatementContext)_localctx).ast =  new AssignmentStatement(((AssignmentStatementContext)_localctx).f.ast, ((AssignmentStatementContext)_localctx).e.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public Block ast;
		public int l;
		public int c;
		public ProgramStatementContext s;
		public TerminalNode LBRACE() { return getToken(BoaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(BoaParser.RBRACE, 0); }
		public List<ProgramStatementContext> programStatement() {
			return getRuleContexts(ProgramStatementContext.class);
		}
		public ProgramStatementContext programStatement(int i) {
			return getRuleContext(ProgramStatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_block);

				((BlockContext)_localctx).l =  getStartLine(); ((BlockContext)_localctx).c =  getStartColumn();
				((BlockContext)_localctx).ast =  new Block();
			
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(470);
			match(LBRACE);
			setState(476);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP) | (1L << SEMICOLON) | (1L << LBRACE) | (1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << NEG))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INV - 64)) | (1L << (DOLLAR - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (CharacterLiteral - 64)) | (1L << (RegexLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (TimeLiteral - 64)) | (1L << (Identifier - 64)))) != 0)) {
				{
				{
				setState(471);
				((BlockContext)_localctx).s = programStatement();
				 _localctx.ast.addStatement(((BlockContext)_localctx).s.ast); 
				}
				}
				setState(478);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(479);
			match(RBRACE);
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public BreakStatement ast;
		public int l;
		public int c;
		public TerminalNode BREAK() { return getToken(BoaParser.BREAK, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitBreakStatement(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_breakStatement);
		 ((BreakStatementContext)_localctx).l =  getStartLine(); ((BreakStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
			match(BREAK);
			 isSemicolon(); ((BreakStatementContext)_localctx).ast =  new BreakStatement(); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStatementContext extends ParserRuleContext {
		public ContinueStatement ast;
		public int l;
		public int c;
		public TerminalNode CONTINUE() { return getToken(BoaParser.CONTINUE, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitContinueStatement(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_continueStatement);
		 ((ContinueStatementContext)_localctx).l =  getStartLine(); ((ContinueStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(484);
			match(CONTINUE);
			 isSemicolon(); ((ContinueStatementContext)_localctx).ast =  new ContinueStatement(); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoStatementContext extends ParserRuleContext {
		public DoStatement ast;
		public int l;
		public int c;
		public StatementContext s;
		public ExpressionContext e;
		public TerminalNode DO() { return getToken(BoaParser.DO, 0); }
		public TerminalNode WHILE() { return getToken(BoaParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DoStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterDoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitDoStatement(this);
		}
	}

	public final DoStatementContext doStatement() throws RecognitionException {
		DoStatementContext _localctx = new DoStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_doStatement);
		 ((DoStatementContext)_localctx).l =  getStartLine(); ((DoStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			match(DO);
			setState(488);
			((DoStatementContext)_localctx).s = statement();
			setState(489);
			match(WHILE);
			setState(490);
			match(LPAREN);
			setState(491);
			((DoStatementContext)_localctx).e = expression();
			setState(492);
			match(RPAREN);
			 isSemicolon(); ((DoStatementContext)_localctx).ast =  new DoStatement(((DoStatementContext)_localctx).e.ast, ((DoStatementContext)_localctx).s.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmitStatementContext extends ParserRuleContext {
		public EmitStatement ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public ExpressionContext e;
		public ExpressionContext w;
		public TerminalNode EMIT() { return getToken(BoaParser.EMIT, 0); }
		public TerminalNode WEIGHT() { return getToken(BoaParser.WEIGHT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> LBRACKET() { return getTokens(BoaParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(BoaParser.LBRACKET, i);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(BoaParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(BoaParser.RBRACKET, i);
		}
		public EmitStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emitStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterEmitStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitEmitStatement(this);
		}
	}

	public final EmitStatementContext emitStatement() throws RecognitionException {
		EmitStatementContext _localctx = new EmitStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_emitStatement);
		 ((EmitStatementContext)_localctx).l =  getStartLine(); ((EmitStatementContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			setState(535);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(495);
				((EmitStatementContext)_localctx).id = identifier();
				 ((EmitStatementContext)_localctx).ast =  new EmitStatement(((EmitStatementContext)_localctx).id.ast); 
				setState(503);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LBRACKET) {
					{
					{
					setState(497);
					match(LBRACKET);
					setState(498);
					((EmitStatementContext)_localctx).e = expression();
					setState(499);
					match(RBRACKET);
					}
					}
					setState(505);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(506);
				match(EMIT);
				 notifyErrorListeners("error: expected 'expression ' before keyword 'weight'"); 
				setState(508);
				match(WEIGHT);
				setState(509);
				((EmitStatementContext)_localctx).w = expression();
				 isSemicolon(); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(512);
				((EmitStatementContext)_localctx).id = identifier();
				 ((EmitStatementContext)_localctx).ast =  new EmitStatement(((EmitStatementContext)_localctx).id.ast); 
				setState(521);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LBRACKET) {
					{
					{
					setState(514);
					match(LBRACKET);
					setState(515);
					((EmitStatementContext)_localctx).e = expression();
					setState(516);
					match(RBRACKET);
					 _localctx.ast.addIndice(((EmitStatementContext)_localctx).e.ast); 
					}
					}
					setState(523);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(524);
				match(EMIT);
				setState(525);
				((EmitStatementContext)_localctx).e = expression();
				 _localctx.ast.setValue(((EmitStatementContext)_localctx).e.ast); 
				setState(531);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(527);
					match(WEIGHT);
					setState(528);
					((EmitStatementContext)_localctx).w = expression();
					 _localctx.ast.setWeight(((EmitStatementContext)_localctx).w.ast); 
					}
					break;
				}
				 isSemicolon(); 
				}
				break;
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatementContext extends ParserRuleContext {
		public ForStatement ast;
		public int l;
		public int c;
		public ForExpressionContext f;
		public ExpressionContext e;
		public ProgramStatementContext s;
		public TerminalNode FOR() { return getToken(BoaParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(BoaParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(BoaParser.SEMICOLON, i);
		}
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public ProgramStatementContext programStatement() {
			return getRuleContext(ProgramStatementContext.class,0);
		}
		public List<ForExpressionContext> forExpression() {
			return getRuleContexts(ForExpressionContext.class);
		}
		public ForExpressionContext forExpression(int i) {
			return getRuleContext(ForExpressionContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitForStatement(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_forStatement);

				((ForStatementContext)_localctx).l =  getStartLine(); ((ForStatementContext)_localctx).c =  getStartColumn();
				((ForStatementContext)_localctx).ast =  new ForStatement();
			
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(537);
			match(FOR);
			setState(538);
			match(LPAREN);
			setState(542);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP) | (1L << LBRACE) | (1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << NEG))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INV - 64)) | (1L << (DOLLAR - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (CharacterLiteral - 64)) | (1L << (RegexLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (TimeLiteral - 64)) | (1L << (Identifier - 64)))) != 0)) {
				{
				setState(539);
				((ForStatementContext)_localctx).f = forExpression();
				 _localctx.ast.setInit(((ForStatementContext)_localctx).f.ast); 
				}
			}

			setState(544);
			match(SEMICOLON);
			setState(548);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP) | (1L << LBRACE) | (1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << NEG))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INV - 64)) | (1L << (DOLLAR - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (CharacterLiteral - 64)) | (1L << (RegexLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (TimeLiteral - 64)) | (1L << (Identifier - 64)))) != 0)) {
				{
				setState(545);
				((ForStatementContext)_localctx).e = expression();
				 _localctx.ast.setCondition(((ForStatementContext)_localctx).e.ast); 
				}
			}

			setState(550);
			match(SEMICOLON);
			setState(554);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP) | (1L << LBRACE) | (1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << NEG))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INV - 64)) | (1L << (DOLLAR - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (CharacterLiteral - 64)) | (1L << (RegexLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (TimeLiteral - 64)) | (1L << (Identifier - 64)))) != 0)) {
				{
				setState(551);
				((ForStatementContext)_localctx).f = forExpression();
				 _localctx.ast.setUpdate(((ForStatementContext)_localctx).f.ast); 
				}
			}

			setState(556);
			match(RPAREN);
			setState(557);
			((ForStatementContext)_localctx).s = programStatement();
			 _localctx.ast.setBody(((ForStatementContext)_localctx).s.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForExpressionContext extends ParserRuleContext {
		public Statement ast;
		public ForVariableDeclarationContext v;
		public ForExpressionStatementContext e;
		public ForVariableDeclarationContext forVariableDeclaration() {
			return getRuleContext(ForVariableDeclarationContext.class,0);
		}
		public ForExpressionStatementContext forExpressionStatement() {
			return getRuleContext(ForExpressionStatementContext.class,0);
		}
		public ForExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterForExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitForExpression(this);
		}
	}

	public final ForExpressionContext forExpression() throws RecognitionException {
		ForExpressionContext _localctx = new ForExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_forExpression);
		try {
			setState(566);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(560);
				((ForExpressionContext)_localctx).v = forVariableDeclaration();
				 ((ForExpressionContext)_localctx).ast =  ((ForExpressionContext)_localctx).v.ast; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(563);
				((ForExpressionContext)_localctx).e = forExpressionStatement();
				 ((ForExpressionContext)_localctx).ast =  ((ForExpressionContext)_localctx).e.ast; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForVariableDeclarationContext extends ParserRuleContext {
		public VarDeclStatement ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public TypeContext t;
		public OutputTypeContext ot;
		public ExpressionContext e;
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(BoaParser.EQUALS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public OutputTypeContext outputType() {
			return getRuleContext(OutputTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterForVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitForVariableDeclaration(this);
		}
	}

	public final ForVariableDeclarationContext forVariableDeclaration() throws RecognitionException {
		ForVariableDeclarationContext _localctx = new ForVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_forVariableDeclaration);
		 ((ForVariableDeclarationContext)_localctx).l =  getStartLine(); ((ForVariableDeclarationContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(568);
			((ForVariableDeclarationContext)_localctx).id = identifier();
			setState(569);
			match(COLON);
			 ((ForVariableDeclarationContext)_localctx).ast =  new VarDeclStatement(((ForVariableDeclarationContext)_localctx).id.ast); 
			setState(574);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(571);
				((ForVariableDeclarationContext)_localctx).t = type();
				 _localctx.ast.setType(((ForVariableDeclarationContext)_localctx).t.ast); 
				}
				break;
			}
			setState(586);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(576);
				match(EQUALS);
				setState(584);
				switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					{
					 notifyErrorListeners("error: output variable declarations should not include '='"); 
					setState(578);
					((ForVariableDeclarationContext)_localctx).ot = outputType();
					 _localctx.ast.setType(((ForVariableDeclarationContext)_localctx).ot.ast); 
					}
					break;
				case 2:
					{
					setState(581);
					((ForVariableDeclarationContext)_localctx).e = expression();
					 _localctx.ast.setInitializer(((ForVariableDeclarationContext)_localctx).e.ast); 
					}
					break;
				}
				}
			}

			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForExpressionStatementContext extends ParserRuleContext {
		public Statement ast;
		public int l;
		public int c;
		public ExpressionContext e;
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INCR() { return getToken(BoaParser.INCR, 0); }
		public TerminalNode DECR() { return getToken(BoaParser.DECR, 0); }
		public ForExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forExpressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterForExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitForExpressionStatement(this);
		}
	}

	public final ForExpressionStatementContext forExpressionStatement() throws RecognitionException {
		ForExpressionStatementContext _localctx = new ForExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_forExpressionStatement);
		 ((ForExpressionStatementContext)_localctx).l =  getStartLine(); ((ForExpressionStatementContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			setState(595);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(588);
				((ForExpressionStatementContext)_localctx).e = expression();
				setState(589);
				((ForExpressionStatementContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==INCR || _la==DECR) ) {
					((ForExpressionStatementContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				 ((ForExpressionStatementContext)_localctx).ast =  new PostfixStatement(((ForExpressionStatementContext)_localctx).e.ast, (((ForExpressionStatementContext)_localctx).op!=null?((ForExpressionStatementContext)_localctx).op.getText():null)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(592);
				((ForExpressionStatementContext)_localctx).e = expression();
				 ((ForExpressionStatementContext)_localctx).ast =  new ExprStatement(((ForExpressionStatementContext)_localctx).e.ast); 
				}
				break;
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public Statement ast;
		public int l;
		public int c;
		public ForExpressionStatementContext e;
		public ForExpressionStatementContext forExpressionStatement() {
			return getRuleContext(ForExpressionStatementContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitExpressionStatement(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_expressionStatement);
		 ((ExpressionStatementContext)_localctx).l =  getStartLine(); ((ExpressionStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(597);
			((ExpressionStatementContext)_localctx).e = forExpressionStatement();
			 isSemicolon(); ((ExpressionStatementContext)_localctx).ast =  ((ExpressionStatementContext)_localctx).e.ast; 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public IfStatement ast;
		public int l;
		public int c;
		public ExpressionContext e;
		public ProgramStatementContext s;
		public ProgramStatementContext els;
		public TerminalNode IF() { return getToken(BoaParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<ProgramStatementContext> programStatement() {
			return getRuleContexts(ProgramStatementContext.class);
		}
		public ProgramStatementContext programStatement(int i) {
			return getRuleContext(ProgramStatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(BoaParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_ifStatement);
		 ((IfStatementContext)_localctx).l =  getStartLine(); ((IfStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(600);
			match(IF);
			setState(601);
			match(LPAREN);
			setState(602);
			((IfStatementContext)_localctx).e = expression();
			setState(603);
			match(RPAREN);
			setState(604);
			((IfStatementContext)_localctx).s = programStatement();
			 ((IfStatementContext)_localctx).ast =  new IfStatement(((IfStatementContext)_localctx).e.ast, ((IfStatementContext)_localctx).s.ast); 
			setState(610);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(606);
				match(ELSE);
				setState(607);
				((IfStatementContext)_localctx).els = programStatement();
				 _localctx.ast.setElse(((IfStatementContext)_localctx).els.ast); 
				}
				break;
			}
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public ReturnStatement ast;
		public int l;
		public int c;
		public ExpressionContext e;
		public TerminalNode RETURN() { return getToken(BoaParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitReturnStatement(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_returnStatement);
		 ((ReturnStatementContext)_localctx).l =  getStartLine(); ((ReturnStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(612);
			match(RETURN);
			 ((ReturnStatementContext)_localctx).ast =  new ReturnStatement(); 
			setState(617);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(614);
				((ReturnStatementContext)_localctx).e = expression();
				 _localctx.ast.setExpr(((ReturnStatementContext)_localctx).e.ast); 
				}
				break;
			}
			 isSemicolon(); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchStatementContext extends ParserRuleContext {
		public SwitchStatement ast;
		public Block b;
		public int l;
		public int c;
		public ExpressionContext e;
		public SwitchCaseContext sc;
		public ProgramStatementContext s;
		public TerminalNode SWITCH() { return getToken(BoaParser.SWITCH, 0); }
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(BoaParser.LBRACE, 0); }
		public TerminalNode DEFAULT() { return getToken(BoaParser.DEFAULT, 0); }
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public TerminalNode RBRACE() { return getToken(BoaParser.RBRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SwitchCaseContext> switchCase() {
			return getRuleContexts(SwitchCaseContext.class);
		}
		public SwitchCaseContext switchCase(int i) {
			return getRuleContext(SwitchCaseContext.class,i);
		}
		public List<ProgramStatementContext> programStatement() {
			return getRuleContexts(ProgramStatementContext.class);
		}
		public ProgramStatementContext programStatement(int i) {
			return getRuleContext(ProgramStatementContext.class,i);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitSwitchStatement(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_switchStatement);
		 ((SwitchStatementContext)_localctx).b =  new Block(); ((SwitchStatementContext)_localctx).l =  getStartLine(); ((SwitchStatementContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(621);
			match(SWITCH);
			setState(622);
			match(LPAREN);
			setState(623);
			((SwitchStatementContext)_localctx).e = expression();
			setState(624);
			match(RPAREN);
			 ((SwitchStatementContext)_localctx).ast =  new SwitchStatement(((SwitchStatementContext)_localctx).e.ast); 
			setState(626);
			match(LBRACE);
			setState(632);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(627);
				((SwitchStatementContext)_localctx).sc = switchCase();
				 _localctx.ast.addCase(((SwitchStatementContext)_localctx).sc.ast); 
				}
				}
				setState(634);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(635);
			match(DEFAULT);
			setState(636);
			match(COLON);
			setState(640); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(637);
				((SwitchStatementContext)_localctx).s = programStatement();
				 _localctx.b.addStatement(((SwitchStatementContext)_localctx).s.ast); 
				}
				}
				setState(642); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP) | (1L << SEMICOLON) | (1L << LBRACE) | (1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << NEG))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INV - 64)) | (1L << (DOLLAR - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (CharacterLiteral - 64)) | (1L << (RegexLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (TimeLiteral - 64)) | (1L << (Identifier - 64)))) != 0) );
			setState(644);
			match(RBRACE);
			 _localctx.ast.setDefault(new SwitchCase(true, _localctx.b)); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchCaseContext extends ParserRuleContext {
		public SwitchCase ast;
		public Block b;
		public int l;
		public int c;
		public ExpressionListContext el;
		public ProgramStatementContext s;
		public TerminalNode CASE() { return getToken(BoaParser.CASE, 0); }
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public List<ProgramStatementContext> programStatement() {
			return getRuleContexts(ProgramStatementContext.class);
		}
		public ProgramStatementContext programStatement(int i) {
			return getRuleContext(ProgramStatementContext.class,i);
		}
		public SwitchCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchCase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterSwitchCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitSwitchCase(this);
		}
	}

	public final SwitchCaseContext switchCase() throws RecognitionException {
		SwitchCaseContext _localctx = new SwitchCaseContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_switchCase);
		 ((SwitchCaseContext)_localctx).b =  new Block(); ((SwitchCaseContext)_localctx).l =  getStartLine(); ((SwitchCaseContext)_localctx).c =  getStartColumn(); 
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(647);
			match(CASE);
			setState(648);
			((SwitchCaseContext)_localctx).el = expressionList();
			 ((SwitchCaseContext)_localctx).ast =  new SwitchCase(false, _localctx.b, ((SwitchCaseContext)_localctx).el.list); 
			setState(650);
			match(COLON);
			setState(654); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(651);
					((SwitchCaseContext)_localctx).s = programStatement();
					 _localctx.b.addStatement(((SwitchCaseContext)_localctx).s.ast); 
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(656); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForeachStatementContext extends ParserRuleContext {
		public ForeachStatement ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public TypeContext t;
		public ExpressionContext e;
		public ProgramStatementContext s;
		public TerminalNode FOREACH() { return getToken(BoaParser.FOREACH, 0); }
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public TerminalNode SEMICOLON() { return getToken(BoaParser.SEMICOLON, 0); }
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ProgramStatementContext programStatement() {
			return getRuleContext(ProgramStatementContext.class,0);
		}
		public ForeachStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreachStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterForeachStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitForeachStatement(this);
		}
	}

	public final ForeachStatementContext foreachStatement() throws RecognitionException {
		ForeachStatementContext _localctx = new ForeachStatementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_foreachStatement);
		 ((ForeachStatementContext)_localctx).l =  getStartLine(); ((ForeachStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(658);
			match(FOREACH);
			setState(659);
			match(LPAREN);
			setState(660);
			((ForeachStatementContext)_localctx).id = identifier();
			setState(661);
			match(COLON);
			setState(662);
			((ForeachStatementContext)_localctx).t = type();
			setState(663);
			match(SEMICOLON);
			setState(664);
			((ForeachStatementContext)_localctx).e = expression();
			setState(665);
			match(RPAREN);
			setState(666);
			((ForeachStatementContext)_localctx).s = programStatement();
			 ((ForeachStatementContext)_localctx).ast =  new ForeachStatement(new Component(((ForeachStatementContext)_localctx).id.ast, ((ForeachStatementContext)_localctx).t.ast), ((ForeachStatementContext)_localctx).e.ast, ((ForeachStatementContext)_localctx).s.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExistsStatementContext extends ParserRuleContext {
		public ExistsStatement ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public TypeContext t;
		public ExpressionContext e;
		public ProgramStatementContext s;
		public TerminalNode EXISTS() { return getToken(BoaParser.EXISTS, 0); }
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public TerminalNode SEMICOLON() { return getToken(BoaParser.SEMICOLON, 0); }
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ProgramStatementContext programStatement() {
			return getRuleContext(ProgramStatementContext.class,0);
		}
		public ExistsStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existsStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterExistsStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitExistsStatement(this);
		}
	}

	public final ExistsStatementContext existsStatement() throws RecognitionException {
		ExistsStatementContext _localctx = new ExistsStatementContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_existsStatement);
		 ((ExistsStatementContext)_localctx).l =  getStartLine(); ((ExistsStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(669);
			match(EXISTS);
			setState(670);
			match(LPAREN);
			setState(671);
			((ExistsStatementContext)_localctx).id = identifier();
			setState(672);
			match(COLON);
			setState(673);
			((ExistsStatementContext)_localctx).t = type();
			setState(674);
			match(SEMICOLON);
			setState(675);
			((ExistsStatementContext)_localctx).e = expression();
			setState(676);
			match(RPAREN);
			setState(677);
			((ExistsStatementContext)_localctx).s = programStatement();
			 ((ExistsStatementContext)_localctx).ast =  new ExistsStatement(new Component(((ExistsStatementContext)_localctx).id.ast, ((ExistsStatementContext)_localctx).t.ast), ((ExistsStatementContext)_localctx).e.ast, ((ExistsStatementContext)_localctx).s.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfallStatementContext extends ParserRuleContext {
		public IfAllStatement ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public TypeContext t;
		public ExpressionContext e;
		public ProgramStatementContext s;
		public TerminalNode IFALL() { return getToken(BoaParser.IFALL, 0); }
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public TerminalNode SEMICOLON() { return getToken(BoaParser.SEMICOLON, 0); }
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ProgramStatementContext programStatement() {
			return getRuleContext(ProgramStatementContext.class,0);
		}
		public IfallStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifallStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterIfallStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitIfallStatement(this);
		}
	}

	public final IfallStatementContext ifallStatement() throws RecognitionException {
		IfallStatementContext _localctx = new IfallStatementContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_ifallStatement);
		 ((IfallStatementContext)_localctx).l =  getStartLine(); ((IfallStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(680);
			match(IFALL);
			setState(681);
			match(LPAREN);
			setState(682);
			((IfallStatementContext)_localctx).id = identifier();
			setState(683);
			match(COLON);
			setState(684);
			((IfallStatementContext)_localctx).t = type();
			setState(685);
			match(SEMICOLON);
			setState(686);
			((IfallStatementContext)_localctx).e = expression();
			setState(687);
			match(RPAREN);
			setState(688);
			((IfallStatementContext)_localctx).s = programStatement();
			 ((IfallStatementContext)_localctx).ast =  new IfAllStatement(new Component(((IfallStatementContext)_localctx).id.ast, ((IfallStatementContext)_localctx).t.ast), ((IfallStatementContext)_localctx).e.ast, ((IfallStatementContext)_localctx).s.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatementContext extends ParserRuleContext {
		public WhileStatement ast;
		public int l;
		public int c;
		public ExpressionContext e;
		public ProgramStatementContext s;
		public TerminalNode WHILE() { return getToken(BoaParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ProgramStatementContext programStatement() {
			return getRuleContext(ProgramStatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitWhileStatement(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_whileStatement);
		 ((WhileStatementContext)_localctx).l =  getStartLine(); ((WhileStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(691);
			match(WHILE);
			setState(692);
			match(LPAREN);
			setState(693);
			((WhileStatementContext)_localctx).e = expression();
			setState(694);
			match(RPAREN);
			setState(695);
			((WhileStatementContext)_localctx).s = programStatement();
			 ((WhileStatementContext)_localctx).ast =  new WhileStatement(((WhileStatementContext)_localctx).e.ast, ((WhileStatementContext)_localctx).s.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VisitStatementContext extends ParserRuleContext {
		public VisitStatement ast;
		public int l;
		public int c;
		public Token b;
		public IdentifierContext id;
		public IdentifierContext t;
		public ProgramStatementContext s;
		public TerminalNode RIGHT_ARROW() { return getToken(BoaParser.RIGHT_ARROW, 0); }
		public TerminalNode AFTER() { return getToken(BoaParser.AFTER, 0); }
		public TerminalNode WILDCARD() { return getToken(BoaParser.WILDCARD, 0); }
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public TerminalNode BEFORE() { return getToken(BoaParser.BEFORE, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public ProgramStatementContext programStatement() {
			return getRuleContext(ProgramStatementContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(BoaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BoaParser.COMMA, i);
		}
		public VisitStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_visitStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterVisitStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitVisitStatement(this);
		}
	}

	public final VisitStatementContext visitStatement() throws RecognitionException {
		VisitStatementContext _localctx = new VisitStatementContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_visitStatement);
		 ((VisitStatementContext)_localctx).l =  getStartLine(); ((VisitStatementContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(701);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(698);
				((VisitStatementContext)_localctx).b = match(BEFORE);
				}
				break;
			case 2:
				{
				setState(699);
				match(AFTER);
				}
				break;
			case 3:
				{
				 notifyErrorListeners("error: visit statements must start with 'before' or 'after'"); 
				}
				break;
			}
			 ((VisitStatementContext)_localctx).ast =  new VisitStatement(((VisitStatementContext)_localctx).b != null); 
			setState(722);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				{
				setState(704);
				match(WILDCARD);
				 _localctx.ast.setWildcard(true); 
				}
				break;
			case 2:
				{
				setState(706);
				((VisitStatementContext)_localctx).id = identifier();
				setState(707);
				match(COLON);
				setState(708);
				((VisitStatementContext)_localctx).t = identifier();
				 _localctx.ast.setComponent(new Component(((VisitStatementContext)_localctx).id.ast, ((VisitStatementContext)_localctx).t.ast)); 
				}
				break;
			case 3:
				{
				setState(711);
				((VisitStatementContext)_localctx).id = identifier();
				 _localctx.ast.addId(((VisitStatementContext)_localctx).id.ast); 
				setState(719);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(713);
					match(COMMA);
					setState(714);
					((VisitStatementContext)_localctx).id = identifier();
					 _localctx.ast.addId(((VisitStatementContext)_localctx).id.ast); 
					}
					}
					setState(721);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(724);
			match(RIGHT_ARROW);
			{
			setState(725);
			((VisitStatementContext)_localctx).s = programStatement();
			 _localctx.ast.setBody(((VisitStatementContext)_localctx).s.ast); 
			}
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StopStatementContext extends ParserRuleContext {
		public StopStatement ast;
		public int l;
		public int c;
		public TerminalNode STOP() { return getToken(BoaParser.STOP, 0); }
		public StopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stopStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterStopStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitStopStatement(this);
		}
	}

	public final StopStatementContext stopStatement() throws RecognitionException {
		StopStatementContext _localctx = new StopStatementContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_stopStatement);
		 ((StopStatementContext)_localctx).l =  getStartLine(); ((StopStatementContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(728);
			match(STOP);
			 isSemicolon(); ((StopStatementContext)_localctx).ast =  new StopStatement(); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Expression ast;
		public int l;
		public int c;
		public ConjunctionContext j;
		public List<ConjunctionContext> conjunction() {
			return getRuleContexts(ConjunctionContext.class);
		}
		public ConjunctionContext conjunction(int i) {
			return getRuleContext(ConjunctionContext.class,i);
		}
		public List<TerminalNode> TWOOR() { return getTokens(BoaParser.TWOOR); }
		public TerminalNode TWOOR(int i) {
			return getToken(BoaParser.TWOOR, i);
		}
		public List<TerminalNode> OR() { return getTokens(BoaParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(BoaParser.OR, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_expression);
		 ((ExpressionContext)_localctx).l =  getStartLine(); ((ExpressionContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(731);
			((ExpressionContext)_localctx).j = conjunction();
			 ((ExpressionContext)_localctx).ast =  new Expression(((ExpressionContext)_localctx).j.ast); 
			setState(739);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR || _la==TWOOR) {
				{
				{
				setState(733);
				_la = _input.LA(1);
				if ( !(_la==OR || _la==TWOOR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(734);
				((ExpressionContext)_localctx).j = conjunction();
				 _localctx.ast.addRhs(((ExpressionContext)_localctx).j.ast); 
				}
				}
				setState(741);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public ArrayList<Expression> list;
		public ExpressionContext e;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BoaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BoaParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitExpressionList(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_expressionList);
		 ((ExpressionListContext)_localctx).list =  new ArrayList<Expression>(); 
		int _la;
		try {
			setState(768);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(742);
				((ExpressionListContext)_localctx).e = expression();
				 _localctx.list.add(((ExpressionListContext)_localctx).e.ast); 
				setState(750);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(744);
					match(COMMA);
					setState(745);
					((ExpressionListContext)_localctx).e = expression();
					 _localctx.list.add(((ExpressionListContext)_localctx).e.ast); 
					}
					}
					setState(752);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(753);
				((ExpressionListContext)_localctx).e = expression();
				 _localctx.list.add(((ExpressionListContext)_localctx).e.ast); 
				setState(765);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP) | (1L << COMMA) | (1L << LBRACE) | (1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << NEG))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INV - 64)) | (1L << (DOLLAR - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (CharacterLiteral - 64)) | (1L << (RegexLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (TimeLiteral - 64)) | (1L << (Identifier - 64)))) != 0)) {
					{
					setState(763);
					switch (_input.LA(1)) {
					case OF:
					case IF:
					case DO:
					case MAP:
					case STACK:
					case SET:
					case FOR:
					case FOREACH:
					case IFALL:
					case EXISTS:
					case NOT:
					case TYPE:
					case ELSE:
					case CASE:
					case OUTPUT:
					case FORMAT:
					case WHILE:
					case BREAK:
					case ARRAY:
					case STATIC:
					case SWITCH:
					case RETURN:
					case WEIGHT:
					case DEFAULT:
					case CONTINUE:
					case FUNCTION:
					case VISITOR:
					case BEFORE:
					case AFTER:
					case STOP:
					case LBRACE:
					case LPAREN:
					case PLUS:
					case MINUS:
					case NEG:
					case INV:
					case DOLLAR:
					case IntegerLiteral:
					case FloatingPointLiteral:
					case CharacterLiteral:
					case RegexLiteral:
					case StringLiteral:
					case TimeLiteral:
					case Identifier:
						{
						 notifyErrorListeners("error: ',' expected"); 
						setState(756);
						((ExpressionListContext)_localctx).e = expression();
						 _localctx.list.add(((ExpressionListContext)_localctx).e.ast); 
						}
						break;
					case COMMA:
						{
						setState(759);
						match(COMMA);
						setState(760);
						((ExpressionListContext)_localctx).e = expression();
						 _localctx.list.add(((ExpressionListContext)_localctx).e.ast); 
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(767);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConjunctionContext extends ParserRuleContext {
		public Conjunction ast;
		public int l;
		public int c;
		public ComparisonContext m;
		public Token op;
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public List<TerminalNode> TWOAND() { return getTokens(BoaParser.TWOAND); }
		public TerminalNode TWOAND(int i) {
			return getToken(BoaParser.TWOAND, i);
		}
		public List<TerminalNode> AND() { return getTokens(BoaParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(BoaParser.AND, i);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_conjunction);
		 ((ConjunctionContext)_localctx).l =  getStartLine(); ((ConjunctionContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(770);
			((ConjunctionContext)_localctx).m = comparison();
			 ((ConjunctionContext)_localctx).ast =  new Conjunction(((ConjunctionContext)_localctx).m.ast); 
			setState(779);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==TWOAND) {
				{
				{
				setState(772);
				((ConjunctionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==AND || _la==TWOAND) ) {
					((ConjunctionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				 _localctx.ast.addOp((((ConjunctionContext)_localctx).op!=null?((ConjunctionContext)_localctx).op.getText():null)); 
				setState(774);
				((ConjunctionContext)_localctx).m = comparison();
				 _localctx.ast.addRhs(((ConjunctionContext)_localctx).m.ast); 
				}
				}
				setState(781);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonContext extends ParserRuleContext {
		public Comparison ast;
		public int l;
		public int c;
		public SimpleExpressionContext e;
		public Token op;
		public SimpleExpressionContext e2;
		public List<SimpleExpressionContext> simpleExpression() {
			return getRuleContexts(SimpleExpressionContext.class);
		}
		public SimpleExpressionContext simpleExpression(int i) {
			return getRuleContext(SimpleExpressionContext.class,i);
		}
		public TerminalNode EQEQ() { return getToken(BoaParser.EQEQ, 0); }
		public TerminalNode NEQ() { return getToken(BoaParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(BoaParser.LT, 0); }
		public TerminalNode LTEQ() { return getToken(BoaParser.LTEQ, 0); }
		public TerminalNode GT() { return getToken(BoaParser.GT, 0); }
		public TerminalNode GTEQ() { return getToken(BoaParser.GTEQ, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_comparison);
		 ((ComparisonContext)_localctx).l =  getStartLine(); ((ComparisonContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			setState(790);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(782);
				((ComparisonContext)_localctx).e = simpleExpression();
				setState(783);
				((ComparisonContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQEQ) | (1L << NEQ) | (1L << LT) | (1L << LTEQ) | (1L << GT) | (1L << GTEQ))) != 0)) ) {
					((ComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(784);
				((ComparisonContext)_localctx).e2 = simpleExpression();
				 ((ComparisonContext)_localctx).ast =  new Comparison(((ComparisonContext)_localctx).e.ast, (((ComparisonContext)_localctx).op!=null?((ComparisonContext)_localctx).op.getText():null), ((ComparisonContext)_localctx).e2.ast); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(787);
				((ComparisonContext)_localctx).e = simpleExpression();
				 ((ComparisonContext)_localctx).ast =  new Comparison(((ComparisonContext)_localctx).e.ast); 
				}
				break;
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleExpressionContext extends ParserRuleContext {
		public SimpleExpr ast;
		public int l;
		public int c;
		public TermContext t;
		public Token op;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(BoaParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(BoaParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(BoaParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(BoaParser.MINUS, i);
		}
		public List<TerminalNode> ONEOR() { return getTokens(BoaParser.ONEOR); }
		public TerminalNode ONEOR(int i) {
			return getToken(BoaParser.ONEOR, i);
		}
		public List<TerminalNode> XOR() { return getTokens(BoaParser.XOR); }
		public TerminalNode XOR(int i) {
			return getToken(BoaParser.XOR, i);
		}
		public SimpleExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterSimpleExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitSimpleExpression(this);
		}
	}

	public final SimpleExpressionContext simpleExpression() throws RecognitionException {
		SimpleExpressionContext _localctx = new SimpleExpressionContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_simpleExpression);
		 ((SimpleExpressionContext)_localctx).l =  getStartLine(); ((SimpleExpressionContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(792);
			((SimpleExpressionContext)_localctx).t = term();
			 ((SimpleExpressionContext)_localctx).ast =  new SimpleExpr(((SimpleExpressionContext)_localctx).t.ast); 
			setState(801);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(794);
					((SimpleExpressionContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ONEOR) | (1L << PLUS) | (1L << MINUS) | (1L << XOR))) != 0)) ) {
						((SimpleExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					} else {
						consume();
					}
					 _localctx.ast.addOp((((SimpleExpressionContext)_localctx).op!=null?((SimpleExpressionContext)_localctx).op.getText():null)); 
					setState(796);
					((SimpleExpressionContext)_localctx).t = term();
					 _localctx.ast.addRhs(((SimpleExpressionContext)_localctx).t.ast); 
					}
					} 
				}
				setState(803);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			}
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Term ast;
		public int l;
		public int c;
		public FactorContext f;
		public Token op;
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(BoaParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(BoaParser.STAR, i);
		}
		public List<TerminalNode> DIV() { return getTokens(BoaParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(BoaParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(BoaParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(BoaParser.MOD, i);
		}
		public List<TerminalNode> EMIT() { return getTokens(BoaParser.EMIT); }
		public TerminalNode EMIT(int i) {
			return getToken(BoaParser.EMIT, i);
		}
		public List<TerminalNode> RSHIFT() { return getTokens(BoaParser.RSHIFT); }
		public TerminalNode RSHIFT(int i) {
			return getToken(BoaParser.RSHIFT, i);
		}
		public List<TerminalNode> ONEAND() { return getTokens(BoaParser.ONEAND); }
		public TerminalNode ONEAND(int i) {
			return getToken(BoaParser.ONEAND, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_term);
		 ((TermContext)_localctx).l =  getStartLine(); ((TermContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(804);
			((TermContext)_localctx).f = factor();
			 ((TermContext)_localctx).ast =  new Term(((TermContext)_localctx).f.ast); 
			setState(813);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & ((1L << (ONEAND - 46)) | (1L << (STAR - 46)) | (1L << (DIV - 46)) | (1L << (MOD - 46)) | (1L << (RSHIFT - 46)) | (1L << (EMIT - 46)))) != 0)) {
				{
				{
				setState(806);
				((TermContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & ((1L << (ONEAND - 46)) | (1L << (STAR - 46)) | (1L << (DIV - 46)) | (1L << (MOD - 46)) | (1L << (RSHIFT - 46)) | (1L << (EMIT - 46)))) != 0)) ) {
					((TermContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				 _localctx.ast.addOp((((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getText():null)); 
				setState(808);
				((TermContext)_localctx).f = factor();
				 _localctx.ast.addRhs(((TermContext)_localctx).f.ast); 
				}
				}
				setState(815);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public Factor ast;
		public int l;
		public int c;
		public OperandContext op;
		public SelectorContext s;
		public IndexContext i;
		public CallContext m;
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public List<SelectorContext> selector() {
			return getRuleContexts(SelectorContext.class);
		}
		public SelectorContext selector(int i) {
			return getRuleContext(SelectorContext.class,i);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public List<CallContext> call() {
			return getRuleContexts(CallContext.class);
		}
		public CallContext call(int i) {
			return getRuleContext(CallContext.class,i);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_factor);
		 ((FactorContext)_localctx).l =  getStartLine(); ((FactorContext)_localctx).c =  getStartColumn(); 
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(816);
			((FactorContext)_localctx).op = operand();
			 ((FactorContext)_localctx).ast =  new Factor(((FactorContext)_localctx).op.ast); 
			setState(829);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(827);
					switch (_input.LA(1)) {
					case DOT:
						{
						setState(818);
						((FactorContext)_localctx).s = selector();
						 _localctx.ast.addOp(((FactorContext)_localctx).s.ast); 
						}
						break;
					case LBRACKET:
						{
						setState(821);
						((FactorContext)_localctx).i = index();
						 _localctx.ast.addOp(((FactorContext)_localctx).i.ast); 
						}
						break;
					case LPAREN:
						{
						setState(824);
						((FactorContext)_localctx).m = call();
						 _localctx.ast.addOp(((FactorContext)_localctx).m.ast); 
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(831);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			}
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectorContext extends ParserRuleContext {
		public Selector ast;
		public int l;
		public int c;
		public IdentifierContext id;
		public TerminalNode DOT() { return getToken(BoaParser.DOT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public SelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitSelector(this);
		}
	}

	public final SelectorContext selector() throws RecognitionException {
		SelectorContext _localctx = new SelectorContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_selector);
		 ((SelectorContext)_localctx).l =  getStartLine(); ((SelectorContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(832);
			match(DOT);
			setState(833);
			((SelectorContext)_localctx).id = identifier();
			 ((SelectorContext)_localctx).ast =  new Selector(((SelectorContext)_localctx).id.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexContext extends ParserRuleContext {
		public Index ast;
		public int l;
		public int c;
		public ExpressionContext s;
		public ExpressionContext end;
		public TerminalNode LBRACKET() { return getToken(BoaParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(BoaParser.RBRACKET, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitIndex(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_index);
		 ((IndexContext)_localctx).l =  getStartLine(); ((IndexContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(836);
			match(LBRACKET);
			setState(837);
			((IndexContext)_localctx).s = expression();
			 ((IndexContext)_localctx).ast =  new Index(((IndexContext)_localctx).s.ast); 
			setState(843);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(839);
				match(COLON);
				setState(840);
				((IndexContext)_localctx).end = expression();
				 _localctx.ast.setEnd(((IndexContext)_localctx).end.ast); 
				}
			}

			setState(845);
			match(RBRACKET);
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallContext extends ParserRuleContext {
		public Call ast;
		public int l;
		public int c;
		public ExpressionListContext el;
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitCall(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_call);
		 ((CallContext)_localctx).l =  getStartLine(); ((CallContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(847);
			match(LPAREN);
			 ((CallContext)_localctx).ast =  new Call(); 
			setState(852);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP) | (1L << LBRACE) | (1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << NEG))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INV - 64)) | (1L << (DOLLAR - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (CharacterLiteral - 64)) | (1L << (RegexLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (TimeLiteral - 64)) | (1L << (Identifier - 64)))) != 0)) {
				{
				setState(849);
				((CallContext)_localctx).el = expressionList();
				 _localctx.ast.setArgs(((CallContext)_localctx).el.list); 
				}
			}

			setState(854);
			match(RPAREN);
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperandContext extends ParserRuleContext {
		public Operand ast;
		public StringLiteralContext s;
		public CharacterLiteralContext c;
		public TimeLiteralContext t;
		public IntegerLiteralContext i;
		public FloatingPointLiteralContext fp;
		public CompositeContext comp;
		public FunctionExpressionContext fe;
		public VisitorExpressionContext v;
		public UnaryFactorContext uf;
		public ParenExpressionContext pe;
		public IdentifierContext id;
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public CharacterLiteralContext characterLiteral() {
			return getRuleContext(CharacterLiteralContext.class,0);
		}
		public TimeLiteralContext timeLiteral() {
			return getRuleContext(TimeLiteralContext.class,0);
		}
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public FloatingPointLiteralContext floatingPointLiteral() {
			return getRuleContext(FloatingPointLiteralContext.class,0);
		}
		public CompositeContext composite() {
			return getRuleContext(CompositeContext.class,0);
		}
		public FunctionExpressionContext functionExpression() {
			return getRuleContext(FunctionExpressionContext.class,0);
		}
		public VisitorExpressionContext visitorExpression() {
			return getRuleContext(VisitorExpressionContext.class,0);
		}
		public UnaryFactorContext unaryFactor() {
			return getRuleContext(UnaryFactorContext.class,0);
		}
		public TerminalNode DOLLAR() { return getToken(BoaParser.DOLLAR, 0); }
		public ParenExpressionContext parenExpression() {
			return getRuleContext(ParenExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitOperand(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_operand);
		try {
			setState(890);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(856);
				((OperandContext)_localctx).s = stringLiteral();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).s.ast; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(859);
				((OperandContext)_localctx).c = characterLiteral();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).c.ast; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(862);
				((OperandContext)_localctx).t = timeLiteral();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).t.ast; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(865);
				((OperandContext)_localctx).i = integerLiteral();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).i.ast; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(868);
				((OperandContext)_localctx).fp = floatingPointLiteral();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).fp.ast; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(871);
				((OperandContext)_localctx).comp = composite();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).comp.ast; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(874);
				((OperandContext)_localctx).fe = functionExpression();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).fe.ast; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(877);
				((OperandContext)_localctx).v = visitorExpression();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).v.ast; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(880);
				((OperandContext)_localctx).uf = unaryFactor();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).uf.ast; 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(883);
				match(DOLLAR);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(884);
				((OperandContext)_localctx).pe = parenExpression();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).pe.ast; 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(887);
				((OperandContext)_localctx).id = identifier();
				 ((OperandContext)_localctx).ast =  ((OperandContext)_localctx).id.ast; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryFactorContext extends ParserRuleContext {
		public UnaryFactor ast;
		public int l;
		public int c;
		public Token op;
		public FactorContext f;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(BoaParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BoaParser.MINUS, 0); }
		public TerminalNode NEG() { return getToken(BoaParser.NEG, 0); }
		public TerminalNode INV() { return getToken(BoaParser.INV, 0); }
		public TerminalNode NOT() { return getToken(BoaParser.NOT, 0); }
		public UnaryFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryFactor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterUnaryFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitUnaryFactor(this);
		}
	}

	public final UnaryFactorContext unaryFactor() throws RecognitionException {
		UnaryFactorContext _localctx = new UnaryFactorContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_unaryFactor);
		 ((UnaryFactorContext)_localctx).l =  getStartLine(); ((UnaryFactorContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(892);
			((UnaryFactorContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & ((1L << (NOT - 11)) | (1L << (PLUS - 11)) | (1L << (MINUS - 11)) | (1L << (NEG - 11)) | (1L << (INV - 11)))) != 0)) ) {
				((UnaryFactorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(893);
			((UnaryFactorContext)_localctx).f = factor();
			 ((UnaryFactorContext)_localctx).ast =  new UnaryFactor((((UnaryFactorContext)_localctx).op!=null?((UnaryFactorContext)_localctx).op.getText():null), ((UnaryFactorContext)_localctx).f.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenExpressionContext extends ParserRuleContext {
		public ParenExpression ast;
		public int l;
		public int c;
		public ExpressionContext e;
		public TerminalNode LPAREN() { return getToken(BoaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BoaParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterParenExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitParenExpression(this);
		}
	}

	public final ParenExpressionContext parenExpression() throws RecognitionException {
		ParenExpressionContext _localctx = new ParenExpressionContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_parenExpression);
		 ((ParenExpressionContext)_localctx).l =  getStartLine(); ((ParenExpressionContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(896);
			match(LPAREN);
			setState(897);
			((ParenExpressionContext)_localctx).e = expression();
			setState(898);
			match(RPAREN);
			 ((ParenExpressionContext)_localctx).ast =  new ParenExpression(((ParenExpressionContext)_localctx).e.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionExpressionContext extends ParserRuleContext {
		public FunctionExpression ast;
		public int l;
		public int c;
		public FunctionTypeContext t;
		public BlockContext b;
		public IdentifierContext id;
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FunctionExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitFunctionExpression(this);
		}
	}

	public final FunctionExpressionContext functionExpression() throws RecognitionException {
		FunctionExpressionContext _localctx = new FunctionExpressionContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_functionExpression);
		 ((FunctionExpressionContext)_localctx).l =  getStartLine(); ((FunctionExpressionContext)_localctx).c =  getStartColumn(); 
		try {
			setState(909);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(901);
				((FunctionExpressionContext)_localctx).t = functionType();
				setState(902);
				((FunctionExpressionContext)_localctx).b = block();
				 ((FunctionExpressionContext)_localctx).ast =  new FunctionExpression(((FunctionExpressionContext)_localctx).t.ast, ((FunctionExpressionContext)_localctx).b.ast); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(905);
				((FunctionExpressionContext)_localctx).id = identifier();
				setState(906);
				((FunctionExpressionContext)_localctx).b = block();
				 ((FunctionExpressionContext)_localctx).ast =  new FunctionExpression(((FunctionExpressionContext)_localctx).id.ast, ((FunctionExpressionContext)_localctx).b.ast); 
				}
				break;
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VisitorExpressionContext extends ParserRuleContext {
		public VisitorExpression ast;
		public Block b;
		public int l;
		public int c;
		public VisitorTypeContext t;
		public VisitStatementContext s;
		public TerminalNode LBRACE() { return getToken(BoaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(BoaParser.RBRACE, 0); }
		public VisitorTypeContext visitorType() {
			return getRuleContext(VisitorTypeContext.class,0);
		}
		public List<ProgramStatementContext> programStatement() {
			return getRuleContexts(ProgramStatementContext.class);
		}
		public ProgramStatementContext programStatement(int i) {
			return getRuleContext(ProgramStatementContext.class,i);
		}
		public List<VisitStatementContext> visitStatement() {
			return getRuleContexts(VisitStatementContext.class);
		}
		public VisitStatementContext visitStatement(int i) {
			return getRuleContext(VisitStatementContext.class,i);
		}
		public VisitorExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_visitorExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterVisitorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitVisitorExpression(this);
		}
	}

	public final VisitorExpressionContext visitorExpression() throws RecognitionException {
		VisitorExpressionContext _localctx = new VisitorExpressionContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_visitorExpression);
		 ((VisitorExpressionContext)_localctx).b =  new Block(); ((VisitorExpressionContext)_localctx).l =  getStartLine(); ((VisitorExpressionContext)_localctx).c =  getStartColumn(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(911);
			((VisitorExpressionContext)_localctx).t = visitorType();
			setState(912);
			match(LBRACE);
			setState(918); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(918);
				switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
				case 1:
					{
					setState(913);
					((VisitorExpressionContext)_localctx).s = visitStatement();
					 _localctx.b.addStatement(((VisitorExpressionContext)_localctx).s.ast); 
					}
					break;
				case 2:
					{
					 notifyErrorListeners("error: only 'before' and 'after' visit statements allowed inside visitor bodies"); 
					setState(917);
					programStatement();
					}
					break;
				}
				}
				setState(920); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OF) | (1L << IF) | (1L << DO) | (1L << MAP) | (1L << STACK) | (1L << SET) | (1L << FOR) | (1L << FOREACH) | (1L << IFALL) | (1L << EXISTS) | (1L << NOT) | (1L << TYPE) | (1L << ELSE) | (1L << CASE) | (1L << OUTPUT) | (1L << FORMAT) | (1L << WHILE) | (1L << BREAK) | (1L << ARRAY) | (1L << STATIC) | (1L << SWITCH) | (1L << RETURN) | (1L << WEIGHT) | (1L << DEFAULT) | (1L << CONTINUE) | (1L << FUNCTION) | (1L << VISITOR) | (1L << BEFORE) | (1L << AFTER) | (1L << STOP) | (1L << SEMICOLON) | (1L << LBRACE) | (1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << NEG))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INV - 64)) | (1L << (WILDCARD - 64)) | (1L << (DOLLAR - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (CharacterLiteral - 64)) | (1L << (RegexLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (TimeLiteral - 64)) | (1L << (Identifier - 64)))) != 0) );
			setState(922);
			match(RBRACE);
			 ((VisitorExpressionContext)_localctx).ast =  new VisitorExpression(((VisitorExpressionContext)_localctx).t.ast, _localctx.b); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompositeContext extends ParserRuleContext {
		public Composite ast;
		public int l;
		public int c;
		public ExpressionListContext el;
		public PairContext p;
		public TerminalNode LBRACE() { return getToken(BoaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(BoaParser.RBRACE, 0); }
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BoaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BoaParser.COMMA, i);
		}
		public CompositeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_composite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterComposite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitComposite(this);
		}
	}

	public final CompositeContext composite() throws RecognitionException {
		CompositeContext _localctx = new CompositeContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_composite);

				((CompositeContext)_localctx).l =  getStartLine(); ((CompositeContext)_localctx).c =  getStartColumn();
				((CompositeContext)_localctx).ast =  new Composite();
			
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(925);
			match(LBRACE);
			setState(943);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
			case 1:
				{
				setState(926);
				((CompositeContext)_localctx).el = expressionList();
				 ((CompositeContext)_localctx).ast =  new Composite(((CompositeContext)_localctx).el.list); 
				}
				break;
			case 2:
				{
				 ((CompositeContext)_localctx).ast =  new Composite(); 
				setState(930);
				((CompositeContext)_localctx).p = pair();
				 _localctx.ast.addPair(((CompositeContext)_localctx).p.ast); 
				setState(938);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(932);
					match(COMMA);
					setState(933);
					((CompositeContext)_localctx).p = pair();
					 _localctx.ast.addPair(((CompositeContext)_localctx).p.ast); 
					}
					}
					setState(940);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				{
				setState(941);
				match(COLON);
				 ((CompositeContext)_localctx).ast =  new Composite(true); 
				}
				break;
			}
			setState(945);
			match(RBRACE);
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairContext extends ParserRuleContext {
		public boa.compiler.ast.Pair ast;
		public int l;
		public int c;
		public ExpressionContext lhs;
		public ExpressionContext rhs;
		public TerminalNode COLON() { return getToken(BoaParser.COLON, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitPair(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_pair);
		 ((PairContext)_localctx).l =  getStartLine(); ((PairContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(947);
			((PairContext)_localctx).lhs = expression();
			setState(948);
			match(COLON);
			setState(949);
			((PairContext)_localctx).rhs = expression();
			 ((PairContext)_localctx).ast =  new boa.compiler.ast.Pair(((PairContext)_localctx).lhs.ast, ((PairContext)_localctx).rhs.ast); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public Identifier ast;
		public int l;
		public int c;
		public Token lit;
		public TerminalNode Identifier() { return getToken(BoaParser.Identifier, 0); }
		public TerminalNode FORMAT() { return getToken(BoaParser.FORMAT, 0); }
		public TerminalNode OF() { return getToken(BoaParser.OF, 0); }
		public TerminalNode IF() { return getToken(BoaParser.IF, 0); }
		public TerminalNode DO() { return getToken(BoaParser.DO, 0); }
		public TerminalNode MAP() { return getToken(BoaParser.MAP, 0); }
		public TerminalNode STACK() { return getToken(BoaParser.STACK, 0); }
		public TerminalNode SET() { return getToken(BoaParser.SET, 0); }
		public TerminalNode FOR() { return getToken(BoaParser.FOR, 0); }
		public TerminalNode FOREACH() { return getToken(BoaParser.FOREACH, 0); }
		public TerminalNode IFALL() { return getToken(BoaParser.IFALL, 0); }
		public TerminalNode EXISTS() { return getToken(BoaParser.EXISTS, 0); }
		public TerminalNode NOT() { return getToken(BoaParser.NOT, 0); }
		public TerminalNode TYPE() { return getToken(BoaParser.TYPE, 0); }
		public TerminalNode ELSE() { return getToken(BoaParser.ELSE, 0); }
		public TerminalNode CASE() { return getToken(BoaParser.CASE, 0); }
		public TerminalNode OUTPUT() { return getToken(BoaParser.OUTPUT, 0); }
		public TerminalNode WHILE() { return getToken(BoaParser.WHILE, 0); }
		public TerminalNode BREAK() { return getToken(BoaParser.BREAK, 0); }
		public TerminalNode ARRAY() { return getToken(BoaParser.ARRAY, 0); }
		public TerminalNode STATIC() { return getToken(BoaParser.STATIC, 0); }
		public TerminalNode SWITCH() { return getToken(BoaParser.SWITCH, 0); }
		public TerminalNode RETURN() { return getToken(BoaParser.RETURN, 0); }
		public TerminalNode WEIGHT() { return getToken(BoaParser.WEIGHT, 0); }
		public TerminalNode DEFAULT() { return getToken(BoaParser.DEFAULT, 0); }
		public TerminalNode CONTINUE() { return getToken(BoaParser.CONTINUE, 0); }
		public TerminalNode FUNCTION() { return getToken(BoaParser.FUNCTION, 0); }
		public TerminalNode VISITOR() { return getToken(BoaParser.VISITOR, 0); }
		public TerminalNode BEFORE() { return getToken(BoaParser.BEFORE, 0); }
		public TerminalNode AFTER() { return getToken(BoaParser.AFTER, 0); }
		public TerminalNode STOP() { return getToken(BoaParser.STOP, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_identifier);
		 ((IdentifierContext)_localctx).l =  getStartLine(); ((IdentifierContext)_localctx).c =  getStartColumn(); ((IdentifierContext)_localctx).ast =  new Identifier("<ERROR>"); 
		try {
			setState(1014);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(952);
				((IdentifierContext)_localctx).lit = match(Identifier);
				 ((IdentifierContext)_localctx).ast =  new Identifier((((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null)); 
				}
				break;
			case FORMAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(954);
				((IdentifierContext)_localctx).lit = match(FORMAT);
				 ((IdentifierContext)_localctx).ast =  new Identifier((((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null)); 
				}
				break;
			case OF:
				enterOuterAlt(_localctx, 3);
				{
				setState(956);
				((IdentifierContext)_localctx).lit = match(OF);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(958);
				((IdentifierContext)_localctx).lit = match(IF);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case DO:
				enterOuterAlt(_localctx, 5);
				{
				setState(960);
				((IdentifierContext)_localctx).lit = match(DO);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case MAP:
				enterOuterAlt(_localctx, 6);
				{
				setState(962);
				((IdentifierContext)_localctx).lit = match(MAP);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case STACK:
				enterOuterAlt(_localctx, 7);
				{
				setState(964);
				((IdentifierContext)_localctx).lit = match(STACK);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 8);
				{
				setState(966);
				((IdentifierContext)_localctx).lit = match(SET);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 9);
				{
				setState(968);
				((IdentifierContext)_localctx).lit = match(FOR);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case FOREACH:
				enterOuterAlt(_localctx, 10);
				{
				setState(970);
				((IdentifierContext)_localctx).lit = match(FOREACH);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case IFALL:
				enterOuterAlt(_localctx, 11);
				{
				setState(972);
				((IdentifierContext)_localctx).lit = match(IFALL);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case EXISTS:
				enterOuterAlt(_localctx, 12);
				{
				setState(974);
				((IdentifierContext)_localctx).lit = match(EXISTS);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 13);
				{
				setState(976);
				((IdentifierContext)_localctx).lit = match(NOT);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case TYPE:
				enterOuterAlt(_localctx, 14);
				{
				setState(978);
				((IdentifierContext)_localctx).lit = match(TYPE);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case ELSE:
				enterOuterAlt(_localctx, 15);
				{
				setState(980);
				((IdentifierContext)_localctx).lit = match(ELSE);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case CASE:
				enterOuterAlt(_localctx, 16);
				{
				setState(982);
				((IdentifierContext)_localctx).lit = match(CASE);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case OUTPUT:
				enterOuterAlt(_localctx, 17);
				{
				setState(984);
				((IdentifierContext)_localctx).lit = match(OUTPUT);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 18);
				{
				setState(986);
				((IdentifierContext)_localctx).lit = match(WHILE);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 19);
				{
				setState(988);
				((IdentifierContext)_localctx).lit = match(BREAK);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case ARRAY:
				enterOuterAlt(_localctx, 20);
				{
				setState(990);
				((IdentifierContext)_localctx).lit = match(ARRAY);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 21);
				{
				setState(992);
				((IdentifierContext)_localctx).lit = match(STATIC);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case SWITCH:
				enterOuterAlt(_localctx, 22);
				{
				setState(994);
				((IdentifierContext)_localctx).lit = match(SWITCH);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 23);
				{
				setState(996);
				((IdentifierContext)_localctx).lit = match(RETURN);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case WEIGHT:
				enterOuterAlt(_localctx, 24);
				{
				setState(998);
				((IdentifierContext)_localctx).lit = match(WEIGHT);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 25);
				{
				setState(1000);
				((IdentifierContext)_localctx).lit = match(DEFAULT);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 26);
				{
				setState(1002);
				((IdentifierContext)_localctx).lit = match(CONTINUE);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case FUNCTION:
				enterOuterAlt(_localctx, 27);
				{
				setState(1004);
				((IdentifierContext)_localctx).lit = match(FUNCTION);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case VISITOR:
				enterOuterAlt(_localctx, 28);
				{
				setState(1006);
				((IdentifierContext)_localctx).lit = match(VISITOR);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case BEFORE:
				enterOuterAlt(_localctx, 29);
				{
				setState(1008);
				((IdentifierContext)_localctx).lit = match(BEFORE);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case AFTER:
				enterOuterAlt(_localctx, 30);
				{
				setState(1010);
				((IdentifierContext)_localctx).lit = match(AFTER);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			case STOP:
				enterOuterAlt(_localctx, 31);
				{
				setState(1012);
				((IdentifierContext)_localctx).lit = match(STOP);
				 notifyErrorListeners("keyword '" + (((IdentifierContext)_localctx).lit!=null?((IdentifierContext)_localctx).lit.getText():null) + "' can not be used as an identifier"); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public IntegerLiteral ast;
		public int l;
		public int c;
		public Token lit;
		public TerminalNode IntegerLiteral() { return getToken(BoaParser.IntegerLiteral, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitIntegerLiteral(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_integerLiteral);
		 ((IntegerLiteralContext)_localctx).l =  getStartLine(); ((IntegerLiteralContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1016);
			((IntegerLiteralContext)_localctx).lit = match(IntegerLiteral);
			 ((IntegerLiteralContext)_localctx).ast =  new IntegerLiteral((((IntegerLiteralContext)_localctx).lit!=null?((IntegerLiteralContext)_localctx).lit.getText():null)); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FloatingPointLiteralContext extends ParserRuleContext {
		public FloatLiteral ast;
		public int l;
		public int c;
		public Token lit;
		public TerminalNode FloatingPointLiteral() { return getToken(BoaParser.FloatingPointLiteral, 0); }
		public FloatingPointLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatingPointLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterFloatingPointLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitFloatingPointLiteral(this);
		}
	}

	public final FloatingPointLiteralContext floatingPointLiteral() throws RecognitionException {
		FloatingPointLiteralContext _localctx = new FloatingPointLiteralContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_floatingPointLiteral);
		 ((FloatingPointLiteralContext)_localctx).l =  getStartLine(); ((FloatingPointLiteralContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1019);
			((FloatingPointLiteralContext)_localctx).lit = match(FloatingPointLiteral);
			 ((FloatingPointLiteralContext)_localctx).ast =  new FloatLiteral((((FloatingPointLiteralContext)_localctx).lit!=null?((FloatingPointLiteralContext)_localctx).lit.getText():null)); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharacterLiteralContext extends ParserRuleContext {
		public CharLiteral ast;
		public int l;
		public int c;
		public Token lit;
		public TerminalNode CharacterLiteral() { return getToken(BoaParser.CharacterLiteral, 0); }
		public CharacterLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_characterLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterCharacterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitCharacterLiteral(this);
		}
	}

	public final CharacterLiteralContext characterLiteral() throws RecognitionException {
		CharacterLiteralContext _localctx = new CharacterLiteralContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_characterLiteral);
		 ((CharacterLiteralContext)_localctx).l =  getStartLine(); ((CharacterLiteralContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1022);
			((CharacterLiteralContext)_localctx).lit = match(CharacterLiteral);
			 ((CharacterLiteralContext)_localctx).ast =  new CharLiteral((((CharacterLiteralContext)_localctx).lit!=null?((CharacterLiteralContext)_localctx).lit.getText():null)); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringLiteralContext extends ParserRuleContext {
		public StringLiteral ast;
		public int l;
		public int c;
		public Token lit;
		public TerminalNode StringLiteral() { return getToken(BoaParser.StringLiteral, 0); }
		public TerminalNode RegexLiteral() { return getToken(BoaParser.RegexLiteral, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitStringLiteral(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_stringLiteral);
		 ((StringLiteralContext)_localctx).l =  getStartLine(); ((StringLiteralContext)_localctx).c =  getStartColumn(); 
		try {
			setState(1029);
			switch (_input.LA(1)) {
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(1025);
				((StringLiteralContext)_localctx).lit = match(StringLiteral);
				 ((StringLiteralContext)_localctx).ast =  new StringLiteral(false, (((StringLiteralContext)_localctx).lit!=null?((StringLiteralContext)_localctx).lit.getText():null)); 
				}
				break;
			case RegexLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(1027);
				((StringLiteralContext)_localctx).lit = match(RegexLiteral);
				 ((StringLiteralContext)_localctx).ast =  new StringLiteral(true, (((StringLiteralContext)_localctx).lit!=null?((StringLiteralContext)_localctx).lit.getText():null)); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TimeLiteralContext extends ParserRuleContext {
		public TimeLiteral ast;
		public int l;
		public int c;
		public Token lit;
		public TerminalNode TimeLiteral() { return getToken(BoaParser.TimeLiteral, 0); }
		public TimeLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timeLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).enterTimeLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoaListener ) ((BoaListener)listener).exitTimeLiteral(this);
		}
	}

	public final TimeLiteralContext timeLiteral() throws RecognitionException {
		TimeLiteralContext _localctx = new TimeLiteralContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_timeLiteral);
		 ((TimeLiteralContext)_localctx).l =  getStartLine(); ((TimeLiteralContext)_localctx).c =  getStartColumn(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1031);
			((TimeLiteralContext)_localctx).lit = match(TimeLiteral);
			 ((TimeLiteralContext)_localctx).ast =  new TimeLiteral((((TimeLiteralContext)_localctx).lit!=null?((TimeLiteralContext)_localctx).lit.getText():null)); 
			}
			 _localctx.ast.setPositions(_localctx.l, _localctx.c, getEndLine(), getEndColumn()); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3Q\u040d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\3\2\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\6\3\u0090\n\3\r\3\16\3\u0091\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u009a\n\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00a5\n\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\5\t\u00d5\n\t\3\n\3\n\3\n\3\n\5\n\u00db\n\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\7\r\u00f1\n\r\f\r\16\r\u00f4\13\r\3\r\5\r\u00f7\n\r\5\r\u00f9"+
		"\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0105\n\16\f"+
		"\16\16\16\u0108\13\16\3\16\5\16\u010b\n\16\5\16\u010d\n\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\5\17\u0116\n\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u0130\n\23\3\23\3\23\3\23\3\23\3\23\5\23\u0137"+
		"\n\23\3\23\3\23\3\23\3\23\3\23\7\23\u013e\n\23\f\23\16\23\u0141\13\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u014a\n\23\3\23\3\23\3\23\3\23"+
		"\3\23\5\23\u0151\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0165\n\25\f\25\16\25\u0168"+
		"\13\25\5\25\u016a\n\25\3\25\3\25\3\25\3\25\3\25\5\25\u0171\n\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u017d\n\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0189\n\25\f\25\16\25\u018c"+
		"\13\25\5\25\u018e\n\25\3\25\3\25\3\25\3\25\3\25\5\25\u0195\n\25\5\25\u0197"+
		"\n\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27"+
		"\u01cf\n\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\7\32\u01dd\n\32\f\32\16\32\u01e0\13\32\3\32\3\32\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\7\36\u01f8\n\36\f\36\16\36\u01fb\13\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u020a\n\36\f\36\16"+
		"\36\u020d\13\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u0216\n\36\3\36"+
		"\3\36\5\36\u021a\n\36\3\37\3\37\3\37\3\37\3\37\5\37\u0221\n\37\3\37\3"+
		"\37\3\37\3\37\5\37\u0227\n\37\3\37\3\37\3\37\3\37\5\37\u022d\n\37\3\37"+
		"\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \5 \u0239\n \3!\3!\3!\3!\3!\3!\5!\u0241"+
		"\n!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u024b\n!\5!\u024d\n!\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\5\"\u0256\n\"\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u0265"+
		"\n$\3%\3%\3%\3%\3%\5%\u026c\n%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3&\7&\u0279"+
		"\n&\f&\16&\u027c\13&\3&\3&\3&\3&\3&\6&\u0283\n&\r&\16&\u0284\3&\3&\3&"+
		"\3\'\3\'\3\'\3\'\3\'\3\'\3\'\6\'\u0291\n\'\r\'\16\'\u0292\3(\3(\3(\3("+
		"\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*"+
		"\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\5,\u02c0\n,\3,\3,\3,"+
		"\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\7,\u02d0\n,\f,\16,\u02d3\13,\5,\u02d5"+
		"\n,\3,\3,\3,\3,\3-\3-\3-\3.\3.\3.\3.\3.\3.\7.\u02e4\n.\f.\16.\u02e7\13"+
		".\3/\3/\3/\3/\3/\3/\7/\u02ef\n/\f/\16/\u02f2\13/\3/\3/\3/\3/\3/\3/\3/"+
		"\3/\3/\3/\7/\u02fe\n/\f/\16/\u0301\13/\5/\u0303\n/\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\7\60\u030c\n\60\f\60\16\60\u030f\13\60\3\61\3\61\3\61"+
		"\3\61\3\61\3\61\3\61\3\61\5\61\u0319\n\61\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\62\7\62\u0322\n\62\f\62\16\62\u0325\13\62\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\7\63\u032e\n\63\f\63\16\63\u0331\13\63\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\3\64\3\64\3\64\7\64\u033e\n\64\f\64\16\64\u0341\13"+
		"\64\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u034e"+
		"\n\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\5\67\u0357\n\67\3\67\3\67\38"+
		"\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38"+
		"\38\38\38\38\38\38\38\38\38\38\58\u037d\n8\39\39\39\39\3:\3:\3:\3:\3:"+
		"\3;\3;\3;\3;\3;\3;\3;\3;\5;\u0390\n;\3<\3<\3<\3<\3<\3<\3<\6<\u0399\n<"+
		"\r<\16<\u039a\3<\3<\3<\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\7=\u03ab\n=\f"+
		"=\16=\u03ae\13=\3=\3=\5=\u03b2\n=\3=\3=\3>\3>\3>\3>\3>\3?\3?\3?\3?\3?"+
		"\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?"+
		"\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?"+
		"\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\5?\u03f9\n?\3@\3@\3@\3A\3A\3A\3B\3B"+
		"\3B\3C\3C\3C\3C\5C\u0408\nC\3D\3D\3D\3D\2\2E\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|"+
		"~\u0080\u0082\u0084\u0086\2\t\3\2\62\63\4\2,,..\4\2//\61\61\3\2\649\4"+
		"\2--:<\5\2\60\60=@GG\5\2\r\r:;AB\u0454\2\u0088\3\2\2\2\4\u008f\3\2\2\2"+
		"\6\u0099\3\2\2\2\b\u00a4\3\2\2\2\n\u00a6\3\2\2\2\f\u00ac\3\2\2\2\16\u00b0"+
		"\3\2\2\2\20\u00d4\3\2\2\2\22\u00da\3\2\2\2\24\u00df\3\2\2\2\26\u00e4\3"+
		"\2\2\2\30\u00e9\3\2\2\2\32\u00fc\3\2\2\2\34\u0115\3\2\2\2\36\u0117\3\2"+
		"\2\2 \u011f\3\2\2\2\"\u0124\3\2\2\2$\u0129\3\2\2\2&\u0152\3\2\2\2(\u0196"+
		"\3\2\2\2*\u0198\3\2\2\2,\u01ce\3\2\2\2.\u01d0\3\2\2\2\60\u01d3\3\2\2\2"+
		"\62\u01d8\3\2\2\2\64\u01e3\3\2\2\2\66\u01e6\3\2\2\28\u01e9\3\2\2\2:\u0219"+
		"\3\2\2\2<\u021b\3\2\2\2>\u0238\3\2\2\2@\u023a\3\2\2\2B\u0255\3\2\2\2D"+
		"\u0257\3\2\2\2F\u025a\3\2\2\2H\u0266\3\2\2\2J\u026f\3\2\2\2L\u0289\3\2"+
		"\2\2N\u0294\3\2\2\2P\u029f\3\2\2\2R\u02aa\3\2\2\2T\u02b5\3\2\2\2V\u02bf"+
		"\3\2\2\2X\u02da\3\2\2\2Z\u02dd\3\2\2\2\\\u0302\3\2\2\2^\u0304\3\2\2\2"+
		"`\u0318\3\2\2\2b\u031a\3\2\2\2d\u0326\3\2\2\2f\u0332\3\2\2\2h\u0342\3"+
		"\2\2\2j\u0346\3\2\2\2l\u0351\3\2\2\2n\u037c\3\2\2\2p\u037e\3\2\2\2r\u0382"+
		"\3\2\2\2t\u038f\3\2\2\2v\u0391\3\2\2\2x\u039f\3\2\2\2z\u03b5\3\2\2\2|"+
		"\u03f8\3\2\2\2~\u03fa\3\2\2\2\u0080\u03fd\3\2\2\2\u0082\u0400\3\2\2\2"+
		"\u0084\u0407\3\2\2\2\u0086\u0409\3\2\2\2\u0088\u0089\5\4\3\2\u0089\u008a"+
		"\7\2\2\3\u008a\u008b\b\2\1\2\u008b\3\3\2\2\2\u008c\u008d\5\6\4\2\u008d"+
		"\u008e\b\3\1\2\u008e\u0090\3\2\2\2\u008f\u008c\3\2\2\2\u0090\u0091\3\2"+
		"\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\5\3\2\2\2\u0093\u0094"+
		"\5\b\5\2\u0094\u0095\b\4\1\2\u0095\u009a\3\2\2\2\u0096\u0097\5,\27\2\u0097"+
		"\u0098\b\4\1\2\u0098\u009a\3\2\2\2\u0099\u0093\3\2\2\2\u0099\u0096\3\2"+
		"\2\2\u009a\7\3\2\2\2\u009b\u009c\5\n\6\2\u009c\u009d\b\5\1\2\u009d\u00a5"+
		"\3\2\2\2\u009e\u009f\5\f\7\2\u009f\u00a0\b\5\1\2\u00a0\u00a5\3\2\2\2\u00a1"+
		"\u00a2\5\16\b\2\u00a2\u00a3\b\5\1\2\u00a3\u00a5\3\2\2\2\u00a4\u009b\3"+
		"\2\2\2\u00a4\u009e\3\2\2\2\u00a4\u00a1\3\2\2\2\u00a5\t\3\2\2\2\u00a6\u00a7"+
		"\7\16\2\2\u00a7\u00a8\5|?\2\u00a8\u00a9\7F\2\2\u00a9\u00aa\5\20\t\2\u00aa"+
		"\u00ab\b\6\1\2\u00ab\13\3\2\2\2\u00ac\u00ad\7\26\2\2\u00ad\u00ae\5\16"+
		"\b\2\u00ae\u00af\b\7\1\2\u00af\r\3\2\2\2\u00b0\u00b1\5@!\2\u00b1\u00b2"+
		"\b\b\1\2\u00b2\17\3\2\2\2\u00b3\u00b4\5\26\f\2\u00b4\u00b5\b\t\1\2\u00b5"+
		"\u00d5\3\2\2\2\u00b6\u00b7\5\36\20\2\u00b7\u00b8\b\t\1\2\u00b8\u00d5\3"+
		"\2\2\2\u00b9\u00ba\5\30\r\2\u00ba\u00bb\b\t\1\2\u00bb\u00d5\3\2\2\2\u00bc"+
		"\u00bd\5$\23\2\u00bd\u00be\b\t\1\2\u00be\u00d5\3\2\2\2\u00bf\u00c0\5("+
		"\25\2\u00c0\u00c1\b\t\1\2\u00c1\u00d5\3\2\2\2\u00c2\u00c3\5*\26\2\u00c3"+
		"\u00c4\b\t\1\2\u00c4\u00d5\3\2\2\2\u00c5\u00c6\5 \21\2\u00c6\u00c7\b\t"+
		"\1\2\u00c7\u00d5\3\2\2\2\u00c8\u00c9\5\"\22\2\u00c9\u00ca\b\t\1\2\u00ca"+
		"\u00d5\3\2\2\2\u00cb\u00cc\5\32\16\2\u00cc\u00cd\b\t\1\2\u00cd\u00d5\3"+
		"\2\2\2\u00ce\u00cf\5&\24\2\u00cf\u00d0\b\t\1\2\u00d0\u00d5\3\2\2\2\u00d1"+
		"\u00d2\5|?\2\u00d2\u00d3\b\t\1\2\u00d3\u00d5\3\2\2\2\u00d4\u00b3\3\2\2"+
		"\2\u00d4\u00b6\3\2\2\2\u00d4\u00b9\3\2\2\2\u00d4\u00bc\3\2\2\2\u00d4\u00bf"+
		"\3\2\2\2\u00d4\u00c2\3\2\2\2\u00d4\u00c5\3\2\2\2\u00d4\u00c8\3\2\2\2\u00d4"+
		"\u00cb\3\2\2\2\u00d4\u00ce\3\2\2\2\u00d4\u00d1\3\2\2\2\u00d5\21\3\2\2"+
		"\2\u00d6\u00d7\5|?\2\u00d7\u00d8\7#\2\2\u00d8\u00d9\b\n\1\2\u00d9\u00db"+
		"\3\2\2\2\u00da\u00d6\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u00dd\5\20\t\2\u00dd\u00de\b\n\1\2\u00de\23\3\2\2\2\u00df\u00e0\5|?\2"+
		"\u00e0\u00e1\7F\2\2\u00e1\u00e2\5Z.\2\u00e2\u00e3\b\13\1\2\u00e3\25\3"+
		"\2\2\2\u00e4\u00e5\7\25\2\2\u00e5\u00e6\7\3\2\2\u00e6\u00e7\5\22\n\2\u00e7"+
		"\u00e8\b\f\1\2\u00e8\27\3\2\2\2\u00e9\u00f8\7&\2\2\u00ea\u00eb\5\34\17"+
		"\2\u00eb\u00f2\b\r\1\2\u00ec\u00ed\7$\2\2\u00ed\u00ee\5\34\17\2\u00ee"+
		"\u00ef\b\r\1\2\u00ef\u00f1\3\2\2\2\u00f0\u00ec\3\2\2\2\u00f1\u00f4\3\2"+
		"\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4"+
		"\u00f2\3\2\2\2\u00f5\u00f7\7$\2\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2"+
		"\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00ea\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fb\7\'\2\2\u00fb\31\3\2\2\2\u00fc\u00fd\7!\2\2"+
		"\u00fd\u010c\7&\2\2\u00fe\u00ff\5\24\13\2\u00ff\u0106\b\16\1\2\u0100\u0101"+
		"\7$\2\2\u0101\u0102\5\24\13\2\u0102\u0103\b\16\1\2\u0103\u0105\3\2\2\2"+
		"\u0104\u0100\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107"+
		"\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0109\u010b\7$\2\2\u010a"+
		"\u0109\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010d\3\2\2\2\u010c\u00fe\3\2"+
		"\2\2\u010c\u010d\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010f\7\'\2\2\u010f"+
		"\33\3\2\2\2\u0110\u0116\5\n\6\2\u0111\u0116\5\f\7\2\u0112\u0113\5\22\n"+
		"\2\u0113\u0114\b\17\1\2\u0114\u0116\3\2\2\2\u0115\u0110\3\2\2\2\u0115"+
		"\u0111\3\2\2\2\u0115\u0112\3\2\2\2\u0116\35\3\2\2\2\u0117\u0118\7\6\2"+
		"\2\u0118\u0119\7*\2\2\u0119\u011a\5\22\n\2\u011a\u011b\7+\2\2\u011b\u011c"+
		"\7\3\2\2\u011c\u011d\5\22\n\2\u011d\u011e\b\20\1\2\u011e\37\3\2\2\2\u011f"+
		"\u0120\7\7\2\2\u0120\u0121\7\3\2\2\u0121\u0122\5\22\n\2\u0122\u0123\b"+
		"\21\1\2\u0123!\3\2\2\2\u0124\u0125\7\b\2\2\u0125\u0126\7\3\2\2\u0126\u0127"+
		"\5\22\n\2\u0127\u0128\b\22\1\2\u0128#\3\2\2\2\u0129\u012f\7\21\2\2\u012a"+
		"\u012b\7\b\2\2\u012b\u0130\b\23\1\2\u012c\u012d\5|?\2\u012d\u012e\b\23"+
		"\1\2\u012e\u0130\3\2\2\2\u012f\u012a\3\2\2\2\u012f\u012c\3\2\2\2\u0130"+
		"\u0136\3\2\2\2\u0131\u0132\7(\2\2\u0132\u0133\5\\/\2\u0133\u0134\7)\2"+
		"\2\u0134\u0135\b\23\1\2\u0135\u0137\3\2\2\2\u0136\u0131\3\2\2\2\u0136"+
		"\u0137\3\2\2\2\u0137\u013f\3\2\2\2\u0138\u0139\7*\2\2\u0139\u013a\5\22"+
		"\n\2\u013a\u013b\7+\2\2\u013b\u013c\b\23\1\2\u013c\u013e\3\2\2\2\u013d"+
		"\u0138\3\2\2\2\u013e\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2"+
		"\2\2\u0140\u0142\3\2\2\2\u0141\u013f\3\2\2\2\u0142\u0143\7\3\2\2\u0143"+
		"\u0144\5\22\n\2\u0144\u0149\b\23\1\2\u0145\u0146\7\31\2\2\u0146\u0147"+
		"\5\22\n\2\u0147\u0148\b\23\1\2\u0148\u014a\3\2\2\2\u0149\u0145\3\2\2\2"+
		"\u0149\u014a\3\2\2\2\u014a\u0150\3\2\2\2\u014b\u014c\7\22\2\2\u014c\u014d"+
		"\7(\2\2\u014d\u014e\5\\/\2\u014e\u014f\7)\2\2\u014f\u0151\3\2\2\2\u0150"+
		"\u014b\3\2\2\2\u0150\u0151\3\2\2\2\u0151%\3\2\2\2\u0152\u0153\5|?\2\u0153"+
		"\u0154\b\24\1\2\u0154\u0155\7\3\2\2\u0155\u0156\5\22\n\2\u0156\u0157\b"+
		"\24\1\2\u0157\'\3\2\2\2\u0158\u0159\7\34\2\2\u0159\u0169\7(\2\2\u015a"+
		"\u015b\5|?\2\u015b\u015c\7#\2\2\u015c\u015d\5\20\t\2\u015d\u0166\b\25"+
		"\1\2\u015e\u015f\7$\2\2\u015f\u0160\5|?\2\u0160\u0161\7#\2\2\u0161\u0162"+
		"\5\20\t\2\u0162\u0163\b\25\1\2\u0163\u0165\3\2\2\2\u0164\u015e\3\2\2\2"+
		"\u0165\u0168\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u016a"+
		"\3\2\2\2\u0168\u0166\3\2\2\2\u0169\u015a\3\2\2\2\u0169\u016a\3\2\2\2\u016a"+
		"\u016b\3\2\2\2\u016b\u0170\7)\2\2\u016c\u016d\7#\2\2\u016d\u016e\5\20"+
		"\t\2\u016e\u016f\b\25\1\2\u016f\u0171\3\2\2\2\u0170\u016c\3\2\2\2\u0170"+
		"\u0171\3\2\2\2\u0171\u0197\3\2\2\2\u0172\u0173\7\34\2\2\u0173\u018d\7"+
		"(\2\2\u0174\u0175\5|?\2\u0175\u0176\7#\2\2\u0176\u0177\5\20\t\2\u0177"+
		"\u0178\b\25\1\2\u0178\u017d\3\2\2\2\u0179\u017a\5|?\2\u017a\u017b\b\25"+
		"\1\2\u017b\u017d\3\2\2\2\u017c\u0174\3\2\2\2\u017c\u0179\3\2\2\2\u017d"+
		"\u018a\3\2\2\2\u017e\u017f\7$\2\2\u017f\u0180\5|?\2\u0180\u0181\7#\2\2"+
		"\u0181\u0182\5\20\t\2\u0182\u0183\b\25\1\2\u0183\u0189\3\2\2\2\u0184\u0185"+
		"\7$\2\2\u0185\u0186\5|?\2\u0186\u0187\b\25\1\2\u0187\u0189\3\2\2\2\u0188"+
		"\u017e\3\2\2\2\u0188\u0184\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2"+
		"\2\2\u018a\u018b\3\2\2\2\u018b\u018e\3\2\2\2\u018c\u018a\3\2\2\2\u018d"+
		"\u017c\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0194\7)"+
		"\2\2\u0190\u0191\7#\2\2\u0191\u0192\5\20\t\2\u0192\u0193\b\25\1\2\u0193"+
		"\u0195\3\2\2\2\u0194\u0190\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0197\3\2"+
		"\2\2\u0196\u0158\3\2\2\2\u0196\u0172\3\2\2\2\u0197)\3\2\2\2\u0198\u0199"+
		"\7\35\2\2\u0199\u019a\b\26\1\2\u019a+\3\2\2\2\u019b\u019c\5\62\32\2\u019c"+
		"\u019d\b\27\1\2\u019d\u01cf\3\2\2\2\u019e\u019f\5\60\31\2\u019f\u01a0"+
		"\b\27\1\2\u01a0\u01cf\3\2\2\2\u01a1\u01a2\5\64\33\2\u01a2\u01a3\b\27\1"+
		"\2\u01a3\u01cf\3\2\2\2\u01a4\u01a5\5\66\34\2\u01a5\u01a6\b\27\1\2\u01a6"+
		"\u01cf\3\2\2\2\u01a7\u01a8\5X-\2\u01a8\u01a9\b\27\1\2\u01a9\u01cf\3\2"+
		"\2\2\u01aa\u01ab\58\35\2\u01ab\u01ac\b\27\1\2\u01ac\u01cf\3\2\2\2\u01ad"+
		"\u01ae\5<\37\2\u01ae\u01af\b\27\1\2\u01af\u01cf\3\2\2\2\u01b0\u01b1\5"+
		"F$\2\u01b1\u01b2\b\27\1\2\u01b2\u01cf\3\2\2\2\u01b3\u01b4\5H%\2\u01b4"+
		"\u01b5\b\27\1\2\u01b5\u01cf\3\2\2\2\u01b6\u01b7\5J&\2\u01b7\u01b8\b\27"+
		"\1\2\u01b8\u01cf\3\2\2\2\u01b9\u01ba\5N(\2\u01ba\u01bb\b\27\1\2\u01bb"+
		"\u01cf\3\2\2\2\u01bc\u01bd\5P)\2\u01bd\u01be\b\27\1\2\u01be\u01cf\3\2"+
		"\2\2\u01bf\u01c0\5R*\2\u01c0\u01c1\b\27\1\2\u01c1\u01cf\3\2\2\2\u01c2"+
		"\u01c3\5T+\2\u01c3\u01c4\b\27\1\2\u01c4\u01cf\3\2\2\2\u01c5\u01c6\5.\30"+
		"\2\u01c6\u01c7\b\27\1\2\u01c7\u01cf\3\2\2\2\u01c8\u01c9\5:\36\2\u01c9"+
		"\u01ca\b\27\1\2\u01ca\u01cf\3\2\2\2\u01cb\u01cc\5D#\2\u01cc\u01cd\b\27"+
		"\1\2\u01cd\u01cf\3\2\2\2\u01ce\u019b\3\2\2\2\u01ce\u019e\3\2\2\2\u01ce"+
		"\u01a1\3\2\2\2\u01ce\u01a4\3\2\2\2\u01ce\u01a7\3\2\2\2\u01ce\u01aa\3\2"+
		"\2\2\u01ce\u01ad\3\2\2\2\u01ce\u01b0\3\2\2\2\u01ce\u01b3\3\2\2\2\u01ce"+
		"\u01b6\3\2\2\2\u01ce\u01b9\3\2\2\2\u01ce\u01bc\3\2\2\2\u01ce\u01bf\3\2"+
		"\2\2\u01ce\u01c2\3\2\2\2\u01ce\u01c5\3\2\2\2\u01ce\u01c8\3\2\2\2\u01ce"+
		"\u01cb\3\2\2\2\u01cf-\3\2\2\2\u01d0\u01d1\7\"\2\2\u01d1\u01d2\b\30\1\2"+
		"\u01d2/\3\2\2\2\u01d3\u01d4\5f\64\2\u01d4\u01d5\7F\2\2\u01d5\u01d6\5Z"+
		".\2\u01d6\u01d7\b\31\1\2\u01d7\61\3\2\2\2\u01d8\u01de\7&\2\2\u01d9\u01da"+
		"\5\6\4\2\u01da\u01db\b\32\1\2\u01db\u01dd\3\2\2\2\u01dc\u01d9\3\2\2\2"+
		"\u01dd\u01e0\3\2\2\2\u01de\u01dc\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01e1"+
		"\3\2\2\2\u01e0\u01de\3\2\2\2\u01e1\u01e2\7\'\2\2\u01e2\63\3\2\2\2\u01e3"+
		"\u01e4\7\24\2\2\u01e4\u01e5\b\33\1\2\u01e5\65\3\2\2\2\u01e6\u01e7\7\33"+
		"\2\2\u01e7\u01e8\b\34\1\2\u01e8\67\3\2\2\2\u01e9\u01ea\7\5\2\2\u01ea\u01eb"+
		"\5,\27\2\u01eb\u01ec\7\23\2\2\u01ec\u01ed\7(\2\2\u01ed\u01ee\5Z.\2\u01ee"+
		"\u01ef\7)\2\2\u01ef\u01f0\b\35\1\2\u01f09\3\2\2\2\u01f1\u01f2\5|?\2\u01f2"+
		"\u01f9\b\36\1\2\u01f3\u01f4\7*\2\2\u01f4\u01f5\5Z.\2\u01f5\u01f6\7+\2"+
		"\2\u01f6\u01f8\3\2\2\2\u01f7\u01f3\3\2\2\2\u01f8\u01fb\3\2\2\2\u01f9\u01f7"+
		"\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fc\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fc"+
		"\u01fd\7G\2\2\u01fd\u01fe\b\36\1\2\u01fe\u01ff\7\31\2\2\u01ff\u0200\5"+
		"Z.\2\u0200\u0201\b\36\1\2\u0201\u021a\3\2\2\2\u0202\u0203\5|?\2\u0203"+
		"\u020b\b\36\1\2\u0204\u0205\7*\2\2\u0205\u0206\5Z.\2\u0206\u0207\7+\2"+
		"\2\u0207\u0208\b\36\1\2\u0208\u020a\3\2\2\2\u0209\u0204\3\2\2\2\u020a"+
		"\u020d\3\2\2\2\u020b\u0209\3\2\2\2\u020b\u020c\3\2\2\2\u020c\u020e\3\2"+
		"\2\2\u020d\u020b\3\2\2\2\u020e\u020f\7G\2\2\u020f\u0210\5Z.\2\u0210\u0215"+
		"\b\36\1\2\u0211\u0212\7\31\2\2\u0212\u0213\5Z.\2\u0213\u0214\b\36\1\2"+
		"\u0214\u0216\3\2\2\2\u0215\u0211\3\2\2\2\u0215\u0216\3\2\2\2\u0216\u0217"+
		"\3\2\2\2\u0217\u0218\b\36\1\2\u0218\u021a\3\2\2\2\u0219\u01f1\3\2\2\2"+
		"\u0219\u0202\3\2\2\2\u021a;\3\2\2\2\u021b\u021c\7\t\2\2\u021c\u0220\7"+
		"(\2\2\u021d\u021e\5> \2\u021e\u021f\b\37\1\2\u021f\u0221\3\2\2\2\u0220"+
		"\u021d\3\2\2\2\u0220\u0221\3\2\2\2\u0221\u0222\3\2\2\2\u0222\u0226\7\""+
		"\2\2\u0223\u0224\5Z.\2\u0224\u0225\b\37\1\2\u0225\u0227\3\2\2\2\u0226"+
		"\u0223\3\2\2\2\u0226\u0227\3\2\2\2\u0227\u0228\3\2\2\2\u0228\u022c\7\""+
		"\2\2\u0229\u022a\5> \2\u022a\u022b\b\37\1\2\u022b\u022d\3\2\2\2\u022c"+
		"\u0229\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u022e\3\2\2\2\u022e\u022f\7)"+
		"\2\2\u022f\u0230\5\6\4\2\u0230\u0231\b\37\1\2\u0231=\3\2\2\2\u0232\u0233"+
		"\5@!\2\u0233\u0234\b \1\2\u0234\u0239\3\2\2\2\u0235\u0236\5B\"\2\u0236"+
		"\u0237\b \1\2\u0237\u0239\3\2\2\2\u0238\u0232\3\2\2\2\u0238\u0235\3\2"+
		"\2\2\u0239?\3\2\2\2\u023a\u023b\5|?\2\u023b\u023c\7#\2\2\u023c\u0240\b"+
		"!\1\2\u023d\u023e\5\20\t\2\u023e\u023f\b!\1\2\u023f\u0241\3\2\2\2\u0240"+
		"\u023d\3\2\2\2\u0240\u0241\3\2\2\2\u0241\u024c\3\2\2\2\u0242\u024a\7F"+
		"\2\2\u0243\u0244\b!\1\2\u0244\u0245\5$\23\2\u0245\u0246\b!\1\2\u0246\u024b"+
		"\3\2\2\2\u0247\u0248\5Z.\2\u0248\u0249\b!\1\2\u0249\u024b\3\2\2\2\u024a"+
		"\u0243\3\2\2\2\u024a\u0247\3\2\2\2\u024b\u024d\3\2\2\2\u024c\u0242\3\2"+
		"\2\2\u024c\u024d\3\2\2\2\u024dA\3\2\2\2\u024e\u024f\5Z.\2\u024f\u0250"+
		"\t\2\2\2\u0250\u0251\b\"\1\2\u0251\u0256\3\2\2\2\u0252\u0253\5Z.\2\u0253"+
		"\u0254\b\"\1\2\u0254\u0256\3\2\2\2\u0255\u024e\3\2\2\2\u0255\u0252\3\2"+
		"\2\2\u0256C\3\2\2\2\u0257\u0258\5B\"\2\u0258\u0259\b#\1\2\u0259E\3\2\2"+
		"\2\u025a\u025b\7\4\2\2\u025b\u025c\7(\2\2\u025c\u025d\5Z.\2\u025d\u025e"+
		"\7)\2\2\u025e\u025f\5\6\4\2\u025f\u0264\b$\1\2\u0260\u0261\7\17\2\2\u0261"+
		"\u0262\5\6\4\2\u0262\u0263\b$\1\2\u0263\u0265\3\2\2\2\u0264\u0260\3\2"+
		"\2\2\u0264\u0265\3\2\2\2\u0265G\3\2\2\2\u0266\u0267\7\30\2\2\u0267\u026b"+
		"\b%\1\2\u0268\u0269\5Z.\2\u0269\u026a\b%\1\2\u026a\u026c\3\2\2\2\u026b"+
		"\u0268\3\2\2\2\u026b\u026c\3\2\2\2\u026c\u026d\3\2\2\2\u026d\u026e\b%"+
		"\1\2\u026eI\3\2\2\2\u026f\u0270\7\27\2\2\u0270\u0271\7(\2\2\u0271\u0272"+
		"\5Z.\2\u0272\u0273\7)\2\2\u0273\u0274\b&\1\2\u0274\u027a\7&\2\2\u0275"+
		"\u0276\5L\'\2\u0276\u0277\b&\1\2\u0277\u0279\3\2\2\2\u0278\u0275\3\2\2"+
		"\2\u0279\u027c\3\2\2\2\u027a\u0278\3\2\2\2\u027a\u027b\3\2\2\2\u027b\u027d"+
		"\3\2\2\2\u027c\u027a\3\2\2\2\u027d\u027e\7\32\2\2\u027e\u0282\7#\2\2\u027f"+
		"\u0280\5\6\4\2\u0280\u0281\b&\1\2\u0281\u0283\3\2\2\2\u0282\u027f\3\2"+
		"\2\2\u0283\u0284\3\2\2\2\u0284\u0282\3\2\2\2\u0284\u0285\3\2\2\2\u0285"+
		"\u0286\3\2\2\2\u0286\u0287\7\'\2\2\u0287\u0288\b&\1\2\u0288K\3\2\2\2\u0289"+
		"\u028a\7\20\2\2\u028a\u028b\5\\/\2\u028b\u028c\b\'\1\2\u028c\u0290\7#"+
		"\2\2\u028d\u028e\5\6\4\2\u028e\u028f\b\'\1\2\u028f\u0291\3\2\2\2\u0290"+
		"\u028d\3\2\2\2\u0291\u0292\3\2\2\2\u0292\u0290\3\2\2\2\u0292\u0293\3\2"+
		"\2\2\u0293M\3\2\2\2\u0294\u0295\7\n\2\2\u0295\u0296\7(\2\2\u0296\u0297"+
		"\5|?\2\u0297\u0298\7#\2\2\u0298\u0299\5\20\t\2\u0299\u029a\7\"\2\2\u029a"+
		"\u029b\5Z.\2\u029b\u029c\7)\2\2\u029c\u029d\5\6\4\2\u029d\u029e\b(\1\2"+
		"\u029eO\3\2\2\2\u029f\u02a0\7\f\2\2\u02a0\u02a1\7(\2\2\u02a1\u02a2\5|"+
		"?\2\u02a2\u02a3\7#\2\2\u02a3\u02a4\5\20\t\2\u02a4\u02a5\7\"\2\2\u02a5"+
		"\u02a6\5Z.\2\u02a6\u02a7\7)\2\2\u02a7\u02a8\5\6\4\2\u02a8\u02a9\b)\1\2"+
		"\u02a9Q\3\2\2\2\u02aa\u02ab\7\13\2\2\u02ab\u02ac\7(\2\2\u02ac\u02ad\5"+
		"|?\2\u02ad\u02ae\7#\2\2\u02ae\u02af\5\20\t\2\u02af\u02b0\7\"\2\2\u02b0"+
		"\u02b1\5Z.\2\u02b1\u02b2\7)\2\2\u02b2\u02b3\5\6\4\2\u02b3\u02b4\b*\1\2"+
		"\u02b4S\3\2\2\2\u02b5\u02b6\7\23\2\2\u02b6\u02b7\7(\2\2\u02b7\u02b8\5"+
		"Z.\2\u02b8\u02b9\7)\2\2\u02b9\u02ba\5\6\4\2\u02ba\u02bb\b+\1\2\u02bbU"+
		"\3\2\2\2\u02bc\u02c0\7\36\2\2\u02bd\u02c0\7\37\2\2\u02be\u02c0\b,\1\2"+
		"\u02bf\u02bc\3\2\2\2\u02bf\u02bd\3\2\2\2\u02bf\u02be\3\2\2\2\u02c0\u02c1"+
		"\3\2\2\2\u02c1\u02d4\b,\1\2\u02c2\u02c3\7C\2\2\u02c3\u02d5\b,\1\2\u02c4"+
		"\u02c5\5|?\2\u02c5\u02c6\7#\2\2\u02c6\u02c7\5|?\2\u02c7\u02c8\b,\1\2\u02c8"+
		"\u02d5\3\2\2\2\u02c9\u02ca\5|?\2\u02ca\u02d1\b,\1\2\u02cb\u02cc\7$\2\2"+
		"\u02cc\u02cd\5|?\2\u02cd\u02ce\b,\1\2\u02ce\u02d0\3\2\2\2\u02cf\u02cb"+
		"\3\2\2\2\u02d0\u02d3\3\2\2\2\u02d1\u02cf\3\2\2\2\u02d1\u02d2\3\2\2\2\u02d2"+
		"\u02d5\3\2\2\2\u02d3\u02d1\3\2\2\2\u02d4\u02c2\3\2\2\2\u02d4\u02c4\3\2"+
		"\2\2\u02d4\u02c9\3\2\2\2\u02d5\u02d6\3\2\2\2\u02d6\u02d7\7H\2\2\u02d7"+
		"\u02d8\5\6\4\2\u02d8\u02d9\b,\1\2\u02d9W\3\2\2\2\u02da\u02db\7 \2\2\u02db"+
		"\u02dc\b-\1\2\u02dcY\3\2\2\2\u02dd\u02de\5^\60\2\u02de\u02e5\b.\1\2\u02df"+
		"\u02e0\t\3\2\2\u02e0\u02e1\5^\60\2\u02e1\u02e2\b.\1\2\u02e2\u02e4\3\2"+
		"\2\2\u02e3\u02df\3\2\2\2\u02e4\u02e7\3\2\2\2\u02e5\u02e3\3\2\2\2\u02e5"+
		"\u02e6\3\2\2\2\u02e6[\3\2\2\2\u02e7\u02e5\3\2\2\2\u02e8\u02e9\5Z.\2\u02e9"+
		"\u02f0\b/\1\2\u02ea\u02eb\7$\2\2\u02eb\u02ec\5Z.\2\u02ec\u02ed\b/\1\2"+
		"\u02ed\u02ef\3\2\2\2\u02ee\u02ea\3\2\2\2\u02ef\u02f2\3\2\2\2\u02f0\u02ee"+
		"\3\2\2\2\u02f0\u02f1\3\2\2\2\u02f1\u0303\3\2\2\2\u02f2\u02f0\3\2\2\2\u02f3"+
		"\u02f4\5Z.\2\u02f4\u02ff\b/\1\2\u02f5\u02f6\b/\1\2\u02f6\u02f7\5Z.\2\u02f7"+
		"\u02f8\b/\1\2\u02f8\u02fe\3\2\2\2\u02f9\u02fa\7$\2\2\u02fa\u02fb\5Z.\2"+
		"\u02fb\u02fc\b/\1\2\u02fc\u02fe\3\2\2\2\u02fd\u02f5\3\2\2\2\u02fd\u02f9"+
		"\3\2\2\2\u02fe\u0301\3\2\2\2\u02ff\u02fd\3\2\2\2\u02ff\u0300\3\2\2\2\u0300"+
		"\u0303\3\2\2\2\u0301\u02ff\3\2\2\2\u0302\u02e8\3\2\2\2\u0302\u02f3\3\2"+
		"\2\2\u0303]\3\2\2\2\u0304\u0305\5`\61\2\u0305\u030d\b\60\1\2\u0306\u0307"+
		"\t\4\2\2\u0307\u0308\b\60\1\2\u0308\u0309\5`\61\2\u0309\u030a\b\60\1\2"+
		"\u030a\u030c\3\2\2\2\u030b\u0306\3\2\2\2\u030c\u030f\3\2\2\2\u030d\u030b"+
		"\3\2\2\2\u030d\u030e\3\2\2\2\u030e_\3\2\2\2\u030f\u030d\3\2\2\2\u0310"+
		"\u0311\5b\62\2\u0311\u0312\t\5\2\2\u0312\u0313\5b\62\2\u0313\u0314\b\61"+
		"\1\2\u0314\u0319\3\2\2\2\u0315\u0316\5b\62\2\u0316\u0317\b\61\1\2\u0317"+
		"\u0319\3\2\2\2\u0318\u0310\3\2\2\2\u0318\u0315\3\2\2\2\u0319a\3\2\2\2"+
		"\u031a\u031b\5d\63\2\u031b\u0323\b\62\1\2\u031c\u031d\t\6\2\2\u031d\u031e"+
		"\b\62\1\2\u031e\u031f\5d\63\2\u031f\u0320\b\62\1\2\u0320\u0322\3\2\2\2"+
		"\u0321\u031c\3\2\2\2\u0322\u0325\3\2\2\2\u0323\u0321\3\2\2\2\u0323\u0324"+
		"\3\2\2\2\u0324c\3\2\2\2\u0325\u0323\3\2\2\2\u0326\u0327\5f\64\2\u0327"+
		"\u032f\b\63\1\2\u0328\u0329\t\7\2\2\u0329\u032a\b\63\1\2\u032a\u032b\5"+
		"f\64\2\u032b\u032c\b\63\1\2\u032c\u032e\3\2\2\2\u032d\u0328\3\2\2\2\u032e"+
		"\u0331\3\2\2\2\u032f\u032d\3\2\2\2\u032f\u0330\3\2\2\2\u0330e\3\2\2\2"+
		"\u0331\u032f\3\2\2\2\u0332\u0333\5n8\2\u0333\u033f\b\64\1\2\u0334\u0335"+
		"\5h\65\2\u0335\u0336\b\64\1\2\u0336\u033e\3\2\2\2\u0337\u0338\5j\66\2"+
		"\u0338\u0339\b\64\1\2\u0339\u033e\3\2\2\2\u033a\u033b\5l\67\2\u033b\u033c"+
		"\b\64\1\2\u033c\u033e\3\2\2\2\u033d\u0334\3\2\2\2\u033d\u0337\3\2\2\2"+
		"\u033d\u033a\3\2\2\2\u033e\u0341\3\2\2\2\u033f\u033d\3\2\2\2\u033f\u0340"+
		"\3\2\2\2\u0340g\3\2\2\2\u0341\u033f\3\2\2\2\u0342\u0343\7%\2\2\u0343\u0344"+
		"\5|?\2\u0344\u0345\b\65\1\2\u0345i\3\2\2\2\u0346\u0347\7*\2\2\u0347\u0348"+
		"\5Z.\2\u0348\u034d\b\66\1\2\u0349\u034a\7#\2\2\u034a\u034b\5Z.\2\u034b"+
		"\u034c\b\66\1\2\u034c\u034e\3\2\2\2\u034d\u0349\3\2\2\2\u034d\u034e\3"+
		"\2\2\2\u034e\u034f\3\2\2\2\u034f\u0350\7+\2\2\u0350k\3\2\2\2\u0351\u0352"+
		"\7(\2\2\u0352\u0356\b\67\1\2\u0353\u0354\5\\/\2\u0354\u0355\b\67\1\2\u0355"+
		"\u0357\3\2\2\2\u0356\u0353\3\2\2\2\u0356\u0357\3\2\2\2\u0357\u0358\3\2"+
		"\2\2\u0358\u0359\7)\2\2\u0359m\3\2\2\2\u035a\u035b\5\u0084C\2\u035b\u035c"+
		"\b8\1\2\u035c\u037d\3\2\2\2\u035d\u035e\5\u0082B\2\u035e\u035f\b8\1\2"+
		"\u035f\u037d\3\2\2\2\u0360\u0361\5\u0086D\2\u0361\u0362\b8\1\2\u0362\u037d"+
		"\3\2\2\2\u0363\u0364\5~@\2\u0364\u0365\b8\1\2\u0365\u037d\3\2\2\2\u0366"+
		"\u0367\5\u0080A\2\u0367\u0368\b8\1\2\u0368\u037d\3\2\2\2\u0369\u036a\5"+
		"x=\2\u036a\u036b\b8\1\2\u036b\u037d\3\2\2\2\u036c\u036d\5t;\2\u036d\u036e"+
		"\b8\1\2\u036e\u037d\3\2\2\2\u036f\u0370\5v<\2\u0370\u0371\b8\1\2\u0371"+
		"\u037d\3\2\2\2\u0372\u0373\5p9\2\u0373\u0374\b8\1\2\u0374\u037d\3\2\2"+
		"\2\u0375\u037d\7E\2\2\u0376\u0377\5r:\2\u0377\u0378\b8\1\2\u0378\u037d"+
		"\3\2\2\2\u0379\u037a\5|?\2\u037a\u037b\b8\1\2\u037b\u037d\3\2\2\2\u037c"+
		"\u035a\3\2\2\2\u037c\u035d\3\2\2\2\u037c\u0360\3\2\2\2\u037c\u0363\3\2"+
		"\2\2\u037c\u0366\3\2\2\2\u037c\u0369\3\2\2\2\u037c\u036c\3\2\2\2\u037c"+
		"\u036f\3\2\2\2\u037c\u0372\3\2\2\2\u037c\u0375\3\2\2\2\u037c\u0376\3\2"+
		"\2\2\u037c\u0379\3\2\2\2\u037do\3\2\2\2\u037e\u037f\t\b\2\2\u037f\u0380"+
		"\5f\64\2\u0380\u0381\b9\1\2\u0381q\3\2\2\2\u0382\u0383\7(\2\2\u0383\u0384"+
		"\5Z.\2\u0384\u0385\7)\2\2\u0385\u0386\b:\1\2\u0386s\3\2\2\2\u0387\u0388"+
		"\5(\25\2\u0388\u0389\5\62\32\2\u0389\u038a\b;\1\2\u038a\u0390\3\2\2\2"+
		"\u038b\u038c\5|?\2\u038c\u038d\5\62\32\2\u038d\u038e\b;\1\2\u038e\u0390"+
		"\3\2\2\2\u038f\u0387\3\2\2\2\u038f\u038b\3\2\2\2\u0390u\3\2\2\2\u0391"+
		"\u0392\5*\26\2\u0392\u0398\7&\2\2\u0393\u0394\5V,\2\u0394\u0395\b<\1\2"+
		"\u0395\u0399\3\2\2\2\u0396\u0397\b<\1\2\u0397\u0399\5\6\4\2\u0398\u0393"+
		"\3\2\2\2\u0398\u0396\3\2\2\2\u0399\u039a\3\2\2\2\u039a\u0398\3\2\2\2\u039a"+
		"\u039b\3\2\2\2\u039b\u039c\3\2\2\2\u039c\u039d\7\'\2\2\u039d\u039e\b<"+
		"\1\2\u039ew\3\2\2\2\u039f\u03b1\7&\2\2\u03a0\u03a1\5\\/\2\u03a1\u03a2"+
		"\b=\1\2\u03a2\u03b2\3\2\2\2\u03a3\u03a4\b=\1\2\u03a4\u03a5\5z>\2\u03a5"+
		"\u03ac\b=\1\2\u03a6\u03a7\7$\2\2\u03a7\u03a8\5z>\2\u03a8\u03a9\b=\1\2"+
		"\u03a9\u03ab\3\2\2\2\u03aa\u03a6\3\2\2\2\u03ab\u03ae\3\2\2\2\u03ac\u03aa"+
		"\3\2\2\2\u03ac\u03ad\3\2\2\2\u03ad\u03b2\3\2\2\2\u03ae\u03ac\3\2\2\2\u03af"+
		"\u03b0\7#\2\2\u03b0\u03b2\b=\1\2\u03b1\u03a0\3\2\2\2\u03b1\u03a3\3\2\2"+
		"\2\u03b1\u03af\3\2\2\2\u03b1\u03b2\3\2\2\2\u03b2\u03b3\3\2\2\2\u03b3\u03b4"+
		"\7\'\2\2\u03b4y\3\2\2\2\u03b5\u03b6\5Z.\2\u03b6\u03b7\7#\2\2\u03b7\u03b8"+
		"\5Z.\2\u03b8\u03b9\b>\1\2\u03b9{\3\2\2\2\u03ba\u03bb\7O\2\2\u03bb\u03f9"+
		"\b?\1\2\u03bc\u03bd\7\22\2\2\u03bd\u03f9\b?\1\2\u03be\u03bf\7\3\2\2\u03bf"+
		"\u03f9\b?\1\2\u03c0\u03c1\7\4\2\2\u03c1\u03f9\b?\1\2\u03c2\u03c3\7\5\2"+
		"\2\u03c3\u03f9\b?\1\2\u03c4\u03c5\7\6\2\2\u03c5\u03f9\b?\1\2\u03c6\u03c7"+
		"\7\7\2\2\u03c7\u03f9\b?\1\2\u03c8\u03c9\7\b\2\2\u03c9\u03f9\b?\1\2\u03ca"+
		"\u03cb\7\t\2\2\u03cb\u03f9\b?\1\2\u03cc\u03cd\7\n\2\2\u03cd\u03f9\b?\1"+
		"\2\u03ce\u03cf\7\13\2\2\u03cf\u03f9\b?\1\2\u03d0\u03d1\7\f\2\2\u03d1\u03f9"+
		"\b?\1\2\u03d2\u03d3\7\r\2\2\u03d3\u03f9\b?\1\2\u03d4\u03d5\7\16\2\2\u03d5"+
		"\u03f9\b?\1\2\u03d6\u03d7\7\17\2\2\u03d7\u03f9\b?\1\2\u03d8\u03d9\7\20"+
		"\2\2\u03d9\u03f9\b?\1\2\u03da\u03db\7\21\2\2\u03db\u03f9\b?\1\2\u03dc"+
		"\u03dd\7\23\2\2\u03dd\u03f9\b?\1\2\u03de\u03df\7\24\2\2\u03df\u03f9\b"+
		"?\1\2\u03e0\u03e1\7\25\2\2\u03e1\u03f9\b?\1\2\u03e2\u03e3\7\26\2\2\u03e3"+
		"\u03f9\b?\1\2\u03e4\u03e5\7\27\2\2\u03e5\u03f9\b?\1\2\u03e6\u03e7\7\30"+
		"\2\2\u03e7\u03f9\b?\1\2\u03e8\u03e9\7\31\2\2\u03e9\u03f9\b?\1\2\u03ea"+
		"\u03eb\7\32\2\2\u03eb\u03f9\b?\1\2\u03ec\u03ed\7\33\2\2\u03ed\u03f9\b"+
		"?\1\2\u03ee\u03ef\7\34\2\2\u03ef\u03f9\b?\1\2\u03f0\u03f1\7\35\2\2\u03f1"+
		"\u03f9\b?\1\2\u03f2\u03f3\7\36\2\2\u03f3\u03f9\b?\1\2\u03f4\u03f5\7\37"+
		"\2\2\u03f5\u03f9\b?\1\2\u03f6\u03f7\7 \2\2\u03f7\u03f9\b?\1\2\u03f8\u03ba"+
		"\3\2\2\2\u03f8\u03bc\3\2\2\2\u03f8\u03be\3\2\2\2\u03f8\u03c0\3\2\2\2\u03f8"+
		"\u03c2\3\2\2\2\u03f8\u03c4\3\2\2\2\u03f8\u03c6\3\2\2\2\u03f8\u03c8\3\2"+
		"\2\2\u03f8\u03ca\3\2\2\2\u03f8\u03cc\3\2\2\2\u03f8\u03ce\3\2\2\2\u03f8"+
		"\u03d0\3\2\2\2\u03f8\u03d2\3\2\2\2\u03f8\u03d4\3\2\2\2\u03f8\u03d6\3\2"+
		"\2\2\u03f8\u03d8\3\2\2\2\u03f8\u03da\3\2\2\2\u03f8\u03dc\3\2\2\2\u03f8"+
		"\u03de\3\2\2\2\u03f8\u03e0\3\2\2\2\u03f8\u03e2\3\2\2\2\u03f8\u03e4\3\2"+
		"\2\2\u03f8\u03e6\3\2\2\2\u03f8\u03e8\3\2\2\2\u03f8\u03ea\3\2\2\2\u03f8"+
		"\u03ec\3\2\2\2\u03f8\u03ee\3\2\2\2\u03f8\u03f0\3\2\2\2\u03f8\u03f2\3\2"+
		"\2\2\u03f8\u03f4\3\2\2\2\u03f8\u03f6\3\2\2\2\u03f9}\3\2\2\2\u03fa\u03fb"+
		"\7I\2\2\u03fb\u03fc\b@\1\2\u03fc\177\3\2\2\2\u03fd\u03fe\7J\2\2\u03fe"+
		"\u03ff\bA\1\2\u03ff\u0081\3\2\2\2\u0400\u0401\7K\2\2\u0401\u0402\bB\1"+
		"\2\u0402\u0083\3\2\2\2\u0403\u0404\7M\2\2\u0404\u0408\bC\1\2\u0405\u0406"+
		"\7L\2\2\u0406\u0408\bC\1\2\u0407\u0403\3\2\2\2\u0407\u0405\3\2\2\2\u0408"+
		"\u0085\3\2\2\2\u0409\u040a\7N\2\2\u040a\u040b\bD\1\2\u040b\u0087\3\2\2"+
		"\2G\u0091\u0099\u00a4\u00d4\u00da\u00f2\u00f6\u00f8\u0106\u010a\u010c"+
		"\u0115\u012f\u0136\u013f\u0149\u0150\u0166\u0169\u0170\u017c\u0188\u018a"+
		"\u018d\u0194\u0196\u01ce\u01de\u01f9\u020b\u0215\u0219\u0220\u0226\u022c"+
		"\u0238\u0240\u024a\u024c\u0255\u0264\u026b\u027a\u0284\u0292\u02bf\u02d1"+
		"\u02d4\u02e5\u02f0\u02fd\u02ff\u0302\u030d\u0318\u0323\u032f\u033d\u033f"+
		"\u034d\u0356\u037c\u038f\u0398\u039a\u03ac\u03b1\u03f8\u0407";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}