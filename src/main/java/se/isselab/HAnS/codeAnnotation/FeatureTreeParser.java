package se.isselab.HAnS.codeAnnotation;
// Generated from C:/Users/Taymo/Documents/GitHub/HAnS-LSP/src/FeatureTree.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class FeatureTreeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, LO=2, FEATURENAME=3, OPTIONAL=4;
	public static final int
		RULE_featuretree = 0, RULE_feature = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"featuretree", "feature"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "LO", "FEATURENAME", "OPTIONAL"
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
	public String getGrammarFileName() { return "FeatureTree.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FeatureTreeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeaturetreeContext extends ParserRuleContext {
		public FeatureContext feature() {
			return getRuleContext(FeatureContext.class,0);
		}
		public TerminalNode LO() { return getToken(FeatureTreeParser.LO, 0); }
		public List<TerminalNode> WS() { return getTokens(FeatureTreeParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FeatureTreeParser.WS, i);
		}
		public List<FeaturetreeContext> featuretree() {
			return getRuleContexts(FeaturetreeContext.class);
		}
		public FeaturetreeContext featuretree(int i) {
			return getRuleContext(FeaturetreeContext.class,i);
		}
		public FeaturetreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_featuretree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureTreeListener ) ((FeatureTreeListener)listener).enterFeaturetree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureTreeListener ) ((FeatureTreeListener)listener).exitFeaturetree(this);
		}
	}

	public final FeaturetreeContext featuretree() throws RecognitionException {
		FeaturetreeContext _localctx = new FeaturetreeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_featuretree);
		int _la;
		try {
			int _alt;
			setState(29);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(4);
				feature();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(5);
				feature();
				setState(27);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(8); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(6);
							match(WS);
							setState(7);
							featuretree();
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(10); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					break;
				case 2:
					{
					setState(16);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==WS) {
						{
						{
						setState(12);
						match(WS);
						setState(13);
						featuretree();
						}
						}
						setState(18);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					{
					setState(19);
					match(LO);
					{
					setState(20);
					featuretree();
					}
					setState(23); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(21);
							match(WS);
							setState(22);
							featuretree();
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(25); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					}
					break;
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
		public TerminalNode FEATURENAME() { return getToken(FeatureTreeParser.FEATURENAME, 0); }
		public TerminalNode WS() { return getToken(FeatureTreeParser.WS, 0); }
		public TerminalNode OPTIONAL() { return getToken(FeatureTreeParser.OPTIONAL, 0); }
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureTreeListener ) ((FeatureTreeListener)listener).enterFeature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureTreeListener ) ((FeatureTreeListener)listener).exitFeature(this);
		}
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_feature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(FEATURENAME);
			setState(34);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(32);
				match(WS);
				setState(33);
				match(OPTIONAL);
				}
				break;
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

	public static final String _serializedATN =
		"\u0004\u0001\u0004%\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000\t\b\u0000\u000b"+
		"\u0000\f\u0000\n\u0001\u0000\u0001\u0000\u0005\u0000\u000f\b\u0000\n\u0000"+
		"\f\u0000\u0012\t\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0004\u0000\u0018\b\u0000\u000b\u0000\f\u0000\u0019\u0003\u0000\u001c"+
		"\b\u0000\u0003\u0000\u001e\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001#\b\u0001\u0001\u0001\u0000\u0000\u0002\u0000\u0002\u0000"+
		"\u0000(\u0000\u001d\u0001\u0000\u0000\u0000\u0002\u001f\u0001\u0000\u0000"+
		"\u0000\u0004\u001e\u0003\u0002\u0001\u0000\u0005\u001b\u0003\u0002\u0001"+
		"\u0000\u0006\u0007\u0005\u0001\u0000\u0000\u0007\t\u0003\u0000\u0000\u0000"+
		"\b\u0006\u0001\u0000\u0000\u0000\t\n\u0001\u0000\u0000\u0000\n\b\u0001"+
		"\u0000\u0000\u0000\n\u000b\u0001\u0000\u0000\u0000\u000b\u001c\u0001\u0000"+
		"\u0000\u0000\f\r\u0005\u0001\u0000\u0000\r\u000f\u0003\u0000\u0000\u0000"+
		"\u000e\f\u0001\u0000\u0000\u0000\u000f\u0012\u0001\u0000\u0000\u0000\u0010"+
		"\u000e\u0001\u0000\u0000\u0000\u0010\u0011\u0001\u0000\u0000\u0000\u0011"+
		"\u0013\u0001\u0000\u0000\u0000\u0012\u0010\u0001\u0000\u0000\u0000\u0013"+
		"\u0014\u0005\u0002\u0000\u0000\u0014\u0017\u0003\u0000\u0000\u0000\u0015"+
		"\u0016\u0005\u0001\u0000\u0000\u0016\u0018\u0003\u0000\u0000\u0000\u0017"+
		"\u0015\u0001\u0000\u0000\u0000\u0018\u0019\u0001\u0000\u0000\u0000\u0019"+
		"\u0017\u0001\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a"+
		"\u001c\u0001\u0000\u0000\u0000\u001b\b\u0001\u0000\u0000\u0000\u001b\u0010"+
		"\u0001\u0000\u0000\u0000\u001c\u001e\u0001\u0000\u0000\u0000\u001d\u0004"+
		"\u0001\u0000\u0000\u0000\u001d\u0005\u0001\u0000\u0000\u0000\u001e\u0001"+
		"\u0001\u0000\u0000\u0000\u001f\"\u0005\u0003\u0000\u0000 !\u0005\u0001"+
		"\u0000\u0000!#\u0005\u0004\u0000\u0000\" \u0001\u0000\u0000\u0000\"#\u0001"+
		"\u0000\u0000\u0000#\u0003\u0001\u0000\u0000\u0000\u0006\n\u0010\u0019"+
		"\u001b\u001d\"";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}