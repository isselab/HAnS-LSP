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
		public List<FeaturetreeContext> featuretree() {
			return getRuleContexts(FeaturetreeContext.class);
		}
		public FeaturetreeContext featuretree(int i) {
			return getRuleContext(FeaturetreeContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(FeatureTreeParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FeatureTreeParser.WS, i);
		}
		public TerminalNode OPTIONAL() { return getToken(FeatureTreeParser.OPTIONAL, 0); }
		public TerminalNode LO() { return getToken(FeatureTreeParser.LO, 0); }
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
		return featuretree(0);
	}

	private FeaturetreeContext featuretree(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FeaturetreeContext _localctx = new FeaturetreeContext(_ctx, _parentState);
		FeaturetreeContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_featuretree, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(5);
			feature();
			}
			_ctx.stop = _input.LT(-1);
			setState(37);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FeaturetreeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_featuretree);
					setState(7);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(10);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(8);
						match(WS);
						setState(9);
						match(OPTIONAL);
						}
						break;
					}
					setState(33);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						setState(14); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(12);
								match(WS);
								setState(13);
								featuretree(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(16); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 2:
						{
						setState(22);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(18);
							match(WS);
							setState(19);
							featuretree(0);
							}
							}
							setState(24);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						{
						setState(25);
						match(LO);
						{
						setState(26);
						featuretree(0);
						}
						setState(29); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(27);
								match(WS);
								setState(28);
								featuretree(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(31); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						}
						break;
					}
					}
					} 
				}
				setState(39);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
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
			setState(40);
			match(FEATURENAME);
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(41);
				match(WS);
				setState(42);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return featuretree_sempred((FeaturetreeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean featuretree_sempred(FeaturetreeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0004.\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003"+
		"\u0000\u000b\b\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u000f\b\u0000"+
		"\u000b\u0000\f\u0000\u0010\u0001\u0000\u0001\u0000\u0005\u0000\u0015\b"+
		"\u0000\n\u0000\f\u0000\u0018\t\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0004\u0000\u001e\b\u0000\u000b\u0000\f\u0000\u001f\u0003"+
		"\u0000\"\b\u0000\u0005\u0000$\b\u0000\n\u0000\f\u0000\'\t\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001,\b\u0001\u0001\u0001\u0000"+
		"\u0001\u0000\u0002\u0000\u0002\u0000\u00002\u0000\u0004\u0001\u0000\u0000"+
		"\u0000\u0002(\u0001\u0000\u0000\u0000\u0004\u0005\u0006\u0000\uffff\uffff"+
		"\u0000\u0005\u0006\u0003\u0002\u0001\u0000\u0006%\u0001\u0000\u0000\u0000"+
		"\u0007\n\n\u0001\u0000\u0000\b\t\u0005\u0001\u0000\u0000\t\u000b\u0005"+
		"\u0004\u0000\u0000\n\b\u0001\u0000\u0000\u0000\n\u000b\u0001\u0000\u0000"+
		"\u0000\u000b!\u0001\u0000\u0000\u0000\f\r\u0005\u0001\u0000\u0000\r\u000f"+
		"\u0003\u0000\u0000\u0000\u000e\f\u0001\u0000\u0000\u0000\u000f\u0010\u0001"+
		"\u0000\u0000\u0000\u0010\u000e\u0001\u0000\u0000\u0000\u0010\u0011\u0001"+
		"\u0000\u0000\u0000\u0011\"\u0001\u0000\u0000\u0000\u0012\u0013\u0005\u0001"+
		"\u0000\u0000\u0013\u0015\u0003\u0000\u0000\u0000\u0014\u0012\u0001\u0000"+
		"\u0000\u0000\u0015\u0018\u0001\u0000\u0000\u0000\u0016\u0014\u0001\u0000"+
		"\u0000\u0000\u0016\u0017\u0001\u0000\u0000\u0000\u0017\u0019\u0001\u0000"+
		"\u0000\u0000\u0018\u0016\u0001\u0000\u0000\u0000\u0019\u001a\u0005\u0002"+
		"\u0000\u0000\u001a\u001d\u0003\u0000\u0000\u0000\u001b\u001c\u0005\u0001"+
		"\u0000\u0000\u001c\u001e\u0003\u0000\u0000\u0000\u001d\u001b\u0001\u0000"+
		"\u0000\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000"+
		"\u0000\u0000\u001f \u0001\u0000\u0000\u0000 \"\u0001\u0000\u0000\u0000"+
		"!\u000e\u0001\u0000\u0000\u0000!\u0016\u0001\u0000\u0000\u0000\"$\u0001"+
		"\u0000\u0000\u0000#\u0007\u0001\u0000\u0000\u0000$\'\u0001\u0000\u0000"+
		"\u0000%#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&\u0001\u0001"+
		"\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000(+\u0005\u0003\u0000\u0000"+
		")*\u0005\u0001\u0000\u0000*,\u0005\u0004\u0000\u0000+)\u0001\u0000\u0000"+
		"\u0000+,\u0001\u0000\u0000\u0000,\u0003\u0001\u0000\u0000\u0000\u0007"+
		"\n\u0010\u0016\u001f!%+";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}