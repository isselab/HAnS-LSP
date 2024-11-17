package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/FeatureTree.g4 by ANTLR 4.13.2
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
		WS=1, Newline=2, LO=3, FEATURENAME=4, OPTIONAL=5;
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
			null, null, null, null, null, "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "Newline", "LO", "FEATURENAME", "OPTIONAL"
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
		public List<FeatureContext> feature() {
			return getRuleContexts(FeatureContext.class);
		}
		public FeatureContext feature(int i) {
			return getRuleContext(FeatureContext.class,i);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(5); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(4);
				feature();
				}
				}
				setState(7); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 30L) != 0) );
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
		public TerminalNode Newline() { return getToken(FeatureTreeParser.Newline, 0); }
		public List<TerminalNode> WS() { return getTokens(FeatureTreeParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FeatureTreeParser.WS, i);
		}
		public TerminalNode LO() { return getToken(FeatureTreeParser.LO, 0); }
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
		int _la;
		try {
			setState(38);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(12);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WS) {
					{
					{
					setState(9);
					match(WS);
					}
					}
					setState(14);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LO) {
					{
					setState(15);
					match(LO);
					setState(16);
					match(WS);
					}
				}

				setState(19);
				match(FEATURENAME);
				setState(22);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(20);
					match(WS);
					setState(21);
					match(OPTIONAL);
					}
					break;
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WS) {
					{
					{
					setState(24);
					match(WS);
					}
					}
					setState(29);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(30);
				match(Newline);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WS) {
					{
					{
					setState(31);
					match(WS);
					}
					}
					setState(36);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(37);
				match(Newline);
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
		"\u0004\u0001\u0005)\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001"+
		"\u0000\u0004\u0000\u0006\b\u0000\u000b\u0000\f\u0000\u0007\u0001\u0001"+
		"\u0005\u0001\u000b\b\u0001\n\u0001\f\u0001\u000e\t\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u0012\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001\u0017\b\u0001\u0001\u0001\u0005\u0001\u001a\b\u0001\n\u0001\f\u0001"+
		"\u001d\t\u0001\u0001\u0001\u0001\u0001\u0005\u0001!\b\u0001\n\u0001\f"+
		"\u0001$\t\u0001\u0001\u0001\u0003\u0001\'\b\u0001\u0001\u0001\u0000\u0000"+
		"\u0002\u0000\u0002\u0000\u0000-\u0000\u0005\u0001\u0000\u0000\u0000\u0002"+
		"&\u0001\u0000\u0000\u0000\u0004\u0006\u0003\u0002\u0001\u0000\u0005\u0004"+
		"\u0001\u0000\u0000\u0000\u0006\u0007\u0001\u0000\u0000\u0000\u0007\u0005"+
		"\u0001\u0000\u0000\u0000\u0007\b\u0001\u0000\u0000\u0000\b\u0001\u0001"+
		"\u0000\u0000\u0000\t\u000b\u0005\u0001\u0000\u0000\n\t\u0001\u0000\u0000"+
		"\u0000\u000b\u000e\u0001\u0000\u0000\u0000\f\n\u0001\u0000\u0000\u0000"+
		"\f\r\u0001\u0000\u0000\u0000\r\u0011\u0001\u0000\u0000\u0000\u000e\f\u0001"+
		"\u0000\u0000\u0000\u000f\u0010\u0005\u0003\u0000\u0000\u0010\u0012\u0005"+
		"\u0001\u0000\u0000\u0011\u000f\u0001\u0000\u0000\u0000\u0011\u0012\u0001"+
		"\u0000\u0000\u0000\u0012\u0013\u0001\u0000\u0000\u0000\u0013\u0016\u0005"+
		"\u0004\u0000\u0000\u0014\u0015\u0005\u0001\u0000\u0000\u0015\u0017\u0005"+
		"\u0005\u0000\u0000\u0016\u0014\u0001\u0000\u0000\u0000\u0016\u0017\u0001"+
		"\u0000\u0000\u0000\u0017\u001b\u0001\u0000\u0000\u0000\u0018\u001a\u0005"+
		"\u0001\u0000\u0000\u0019\u0018\u0001\u0000\u0000\u0000\u001a\u001d\u0001"+
		"\u0000\u0000\u0000\u001b\u0019\u0001\u0000\u0000\u0000\u001b\u001c\u0001"+
		"\u0000\u0000\u0000\u001c\u001e\u0001\u0000\u0000\u0000\u001d\u001b\u0001"+
		"\u0000\u0000\u0000\u001e\'\u0005\u0002\u0000\u0000\u001f!\u0005\u0001"+
		"\u0000\u0000 \u001f\u0001\u0000\u0000\u0000!$\u0001\u0000\u0000\u0000"+
		"\" \u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#%\u0001\u0000\u0000"+
		"\u0000$\"\u0001\u0000\u0000\u0000%\'\u0005\u0002\u0000\u0000&\f\u0001"+
		"\u0000\u0000\u0000&\"\u0001\u0000\u0000\u0000\'\u0003\u0001\u0000\u0000"+
		"\u0000\u0007\u0007\f\u0011\u0016\u001b\"&";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}