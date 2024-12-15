package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/FeatureToFile.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class FeatureToFileParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, WS=3, Newline=4, String=5, Kommer=6;
	public static final int
		RULE_document = 0, RULE_mapping = 1, RULE_files = 2, RULE_file = 3, RULE_features = 4, 
		RULE_feature = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"document", "mapping", "files", "file", "features", "feature"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'::'", null, null, null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "WS", "Newline", "String", "Kommer"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "FeatureToFile.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FeatureToFileParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DocumentContext extends ParserRuleContext {
		public List<MappingContext> mapping() {
			return getRuleContexts(MappingContext.class);
		}
		public MappingContext mapping(int i) {
			return getRuleContext(MappingContext.class,i);
		}
		public List<TerminalNode> Newline() { return getTokens(FeatureToFileParser.Newline); }
		public TerminalNode Newline(int i) {
			return getToken(FeatureToFileParser.Newline, i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).enterDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).exitDocument(this);
		}
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_document);
		int _la;
		try {
			setState(20);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(12);
				mapping();
				setState(15); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(13);
					match(Newline);
					setState(14);
					mapping();
					}
					}
					setState(17); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==Newline );
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(19);
				mapping();
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

	@SuppressWarnings("CheckReturnValue")
	public static class MappingContext extends ParserRuleContext {
		public FilesContext files() {
			return getRuleContext(FilesContext.class,0);
		}
		public TerminalNode Newline() { return getToken(FeatureToFileParser.Newline, 0); }
		public FeaturesContext features() {
			return getRuleContext(FeaturesContext.class,0);
		}
		public MappingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapping; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).enterMapping(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).exitMapping(this);
		}
	}

	public final MappingContext mapping() throws RecognitionException {
		MappingContext _localctx = new MappingContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mapping);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(22);
			files();
			setState(23);
			match(Newline);
			setState(24);
			features();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class FilesContext extends ParserRuleContext {
		public List<FileContext> file() {
			return getRuleContexts(FileContext.class);
		}
		public FileContext file(int i) {
			return getRuleContext(FileContext.class,i);
		}
		public List<TerminalNode> Kommer() { return getTokens(FeatureToFileParser.Kommer); }
		public TerminalNode Kommer(int i) {
			return getToken(FeatureToFileParser.Kommer, i);
		}
		public List<TerminalNode> WS() { return getTokens(FeatureToFileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FeatureToFileParser.WS, i);
		}
		public FilesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_files; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).enterFiles(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).exitFiles(this);
		}
	}

	public final FilesContext files() throws RecognitionException {
		FilesContext _localctx = new FilesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_files);
		int _la;
		try {
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(26);
				file();
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(30);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==WS) {
						{
						{
						setState(27);
						match(WS);
						}
						}
						setState(32);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(33);
					match(Kommer);
					setState(37);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==WS) {
						{
						{
						setState(34);
						match(WS);
						}
						}
						setState(39);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(40);
					file();
					}
					}
					setState(43); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS || _la==Kommer );
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(45);
				file();
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

	@SuppressWarnings("CheckReturnValue")
	public static class FileContext extends ParserRuleContext {
		public List<TerminalNode> String() { return getTokens(FeatureToFileParser.String); }
		public TerminalNode String(int i) {
			return getToken(FeatureToFileParser.String, i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_file);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(String);
			setState(49);
			match(T__0);
			setState(50);
			match(String);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FeaturesContext extends ParserRuleContext {
		public List<FeatureContext> feature() {
			return getRuleContexts(FeatureContext.class);
		}
		public FeatureContext feature(int i) {
			return getRuleContext(FeatureContext.class,i);
		}
		public List<TerminalNode> Kommer() { return getTokens(FeatureToFileParser.Kommer); }
		public TerminalNode Kommer(int i) {
			return getToken(FeatureToFileParser.Kommer, i);
		}
		public List<TerminalNode> WS() { return getTokens(FeatureToFileParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FeatureToFileParser.WS, i);
		}
		public FeaturesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_features; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).enterFeatures(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).exitFeatures(this);
		}
	}

	public final FeaturesContext features() throws RecognitionException {
		FeaturesContext _localctx = new FeaturesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_features);
		int _la;
		try {
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(52);
				feature();
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(56);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==WS) {
						{
						{
						setState(53);
						match(WS);
						}
						}
						setState(58);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(59);
					match(Kommer);
					setState(63);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==WS) {
						{
						{
						setState(60);
						match(WS);
						}
						}
						setState(65);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(66);
					feature();
					}
					}
					setState(69); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS || _la==Kommer );
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(71);
				feature();
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

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureContext extends ParserRuleContext {
		public List<TerminalNode> String() { return getTokens(FeatureToFileParser.String); }
		public TerminalNode String(int i) {
			return getToken(FeatureToFileParser.String, i);
		}
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).enterFeature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFileListener ) ((FeatureToFileListener)listener).exitFeature(this);
		}
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_feature);
		int _la;
		try {
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(74);
				match(String);
				setState(77); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(75);
					match(T__1);
					setState(76);
					match(String);
					}
					}
					setState(79); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__1 );
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(81);
				match(String);
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

	public static final String _serializedATN =
		"\u0004\u0001\u0006U\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u0010"+
		"\b\u0000\u000b\u0000\f\u0000\u0011\u0001\u0000\u0003\u0000\u0015\b\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0005\u0002\u001d\b\u0002\n\u0002\f\u0002 \t\u0002\u0001\u0002\u0001"+
		"\u0002\u0005\u0002$\b\u0002\n\u0002\f\u0002\'\t\u0002\u0001\u0002\u0004"+
		"\u0002*\b\u0002\u000b\u0002\f\u0002+\u0001\u0002\u0003\u0002/\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0005\u00047\b\u0004\n\u0004\f\u0004:\t\u0004\u0001\u0004\u0001\u0004"+
		"\u0005\u0004>\b\u0004\n\u0004\f\u0004A\t\u0004\u0001\u0004\u0004\u0004"+
		"D\b\u0004\u000b\u0004\f\u0004E\u0001\u0004\u0003\u0004I\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0004\u0005N\b\u0005\u000b\u0005\f\u0005"+
		"O\u0001\u0005\u0003\u0005S\b\u0005\u0001\u0005\u0000\u0000\u0006\u0000"+
		"\u0002\u0004\u0006\b\n\u0000\u0000Z\u0000\u0014\u0001\u0000\u0000\u0000"+
		"\u0002\u0016\u0001\u0000\u0000\u0000\u0004.\u0001\u0000\u0000\u0000\u0006"+
		"0\u0001\u0000\u0000\u0000\bH\u0001\u0000\u0000\u0000\nR\u0001\u0000\u0000"+
		"\u0000\f\u000f\u0003\u0002\u0001\u0000\r\u000e\u0005\u0004\u0000\u0000"+
		"\u000e\u0010\u0003\u0002\u0001\u0000\u000f\r\u0001\u0000\u0000\u0000\u0010"+
		"\u0011\u0001\u0000\u0000\u0000\u0011\u000f\u0001\u0000\u0000\u0000\u0011"+
		"\u0012\u0001\u0000\u0000\u0000\u0012\u0015\u0001\u0000\u0000\u0000\u0013"+
		"\u0015\u0003\u0002\u0001\u0000\u0014\f\u0001\u0000\u0000\u0000\u0014\u0013"+
		"\u0001\u0000\u0000\u0000\u0015\u0001\u0001\u0000\u0000\u0000\u0016\u0017"+
		"\u0003\u0004\u0002\u0000\u0017\u0018\u0005\u0004\u0000\u0000\u0018\u0019"+
		"\u0003\b\u0004\u0000\u0019\u0003\u0001\u0000\u0000\u0000\u001a)\u0003"+
		"\u0006\u0003\u0000\u001b\u001d\u0005\u0003\u0000\u0000\u001c\u001b\u0001"+
		"\u0000\u0000\u0000\u001d \u0001\u0000\u0000\u0000\u001e\u001c\u0001\u0000"+
		"\u0000\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f!\u0001\u0000\u0000"+
		"\u0000 \u001e\u0001\u0000\u0000\u0000!%\u0005\u0006\u0000\u0000\"$\u0005"+
		"\u0003\u0000\u0000#\"\u0001\u0000\u0000\u0000$\'\u0001\u0000\u0000\u0000"+
		"%#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&(\u0001\u0000\u0000"+
		"\u0000\'%\u0001\u0000\u0000\u0000(*\u0003\u0006\u0003\u0000)\u001e\u0001"+
		"\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000"+
		"+,\u0001\u0000\u0000\u0000,/\u0001\u0000\u0000\u0000-/\u0003\u0006\u0003"+
		"\u0000.\u001a\u0001\u0000\u0000\u0000.-\u0001\u0000\u0000\u0000/\u0005"+
		"\u0001\u0000\u0000\u000001\u0005\u0005\u0000\u000012\u0005\u0001\u0000"+
		"\u000023\u0005\u0005\u0000\u00003\u0007\u0001\u0000\u0000\u00004C\u0003"+
		"\n\u0005\u000057\u0005\u0003\u0000\u000065\u0001\u0000\u0000\u00007:\u0001"+
		"\u0000\u0000\u000086\u0001\u0000\u0000\u000089\u0001\u0000\u0000\u0000"+
		"9;\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000;?\u0005\u0006\u0000"+
		"\u0000<>\u0005\u0003\u0000\u0000=<\u0001\u0000\u0000\u0000>A\u0001\u0000"+
		"\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@B\u0001"+
		"\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BD\u0003\n\u0005\u0000C8\u0001"+
		"\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000"+
		"EF\u0001\u0000\u0000\u0000FI\u0001\u0000\u0000\u0000GI\u0003\n\u0005\u0000"+
		"H4\u0001\u0000\u0000\u0000HG\u0001\u0000\u0000\u0000I\t\u0001\u0000\u0000"+
		"\u0000JM\u0005\u0005\u0000\u0000KL\u0005\u0002\u0000\u0000LN\u0005\u0005"+
		"\u0000\u0000MK\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000OM\u0001"+
		"\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PS\u0001\u0000\u0000\u0000"+
		"QS\u0005\u0005\u0000\u0000RJ\u0001\u0000\u0000\u0000RQ\u0001\u0000\u0000"+
		"\u0000S\u000b\u0001\u0000\u0000\u0000\f\u0011\u0014\u001e%+.8?EHOR";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}