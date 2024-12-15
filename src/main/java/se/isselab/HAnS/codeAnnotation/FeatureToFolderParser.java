package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/FeatureToFolder.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class FeatureToFolderParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, WS=2, Newline=3, FEATURENAME=4, Kommer=5;
	public static final int
		RULE_features = 0, RULE_feature = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"features", "feature"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'::'", null, null, null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "WS", "Newline", "FEATURENAME", "Kommer"
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
	public String getGrammarFileName() { return "FeatureToFolder.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FeatureToFolderParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeaturesContext extends ParserRuleContext {
		public List<FeatureContext> feature() {
			return getRuleContexts(FeatureContext.class);
		}
		public FeatureContext feature(int i) {
			return getRuleContext(FeatureContext.class,i);
		}
		public List<TerminalNode> Kommer() { return getTokens(FeatureToFolderParser.Kommer); }
		public TerminalNode Kommer(int i) {
			return getToken(FeatureToFolderParser.Kommer, i);
		}
		public List<TerminalNode> WS() { return getTokens(FeatureToFolderParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FeatureToFolderParser.WS, i);
		}
		public FeaturesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_features; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFolderListener ) ((FeatureToFolderListener)listener).enterFeatures(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFolderListener ) ((FeatureToFolderListener)listener).exitFeatures(this);
		}
	}

	public final FeaturesContext features() throws RecognitionException {
		FeaturesContext _localctx = new FeaturesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_features);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4);
			feature();
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS || _la==Kommer) {
				{
				{
				setState(8);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WS) {
					{
					{
					setState(5);
					match(WS);
					}
					}
					setState(10);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(11);
				match(Kommer);
				setState(13); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(12);
					match(WS);
					}
					}
					setState(15); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS );
				setState(17);
				feature();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class FeatureContext extends ParserRuleContext {
		public List<TerminalNode> FEATURENAME() { return getTokens(FeatureToFolderParser.FEATURENAME); }
		public TerminalNode FEATURENAME(int i) {
			return getToken(FeatureToFolderParser.FEATURENAME, i);
		}
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFolderListener ) ((FeatureToFolderListener)listener).enterFeature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FeatureToFolderListener ) ((FeatureToFolderListener)listener).exitFeature(this);
		}
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_feature);
		int _la;
		try {
			setState(31);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(23);
				match(FEATURENAME);
				setState(26); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(24);
					match(T__0);
					setState(25);
					match(FEATURENAME);
					}
					}
					setState(28); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__0 );
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(30);
				match(FEATURENAME);
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
		"\u0004\u0001\u0005\"\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001"+
		"\u0000\u0001\u0000\u0005\u0000\u0007\b\u0000\n\u0000\f\u0000\n\t\u0000"+
		"\u0001\u0000\u0001\u0000\u0004\u0000\u000e\b\u0000\u000b\u0000\f\u0000"+
		"\u000f\u0001\u0000\u0005\u0000\u0013\b\u0000\n\u0000\f\u0000\u0016\t\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001\u001b\b\u0001\u000b\u0001"+
		"\f\u0001\u001c\u0001\u0001\u0003\u0001 \b\u0001\u0001\u0001\u0000\u0000"+
		"\u0002\u0000\u0002\u0000\u0000$\u0000\u0004\u0001\u0000\u0000\u0000\u0002"+
		"\u001f\u0001\u0000\u0000\u0000\u0004\u0014\u0003\u0002\u0001\u0000\u0005"+
		"\u0007\u0005\u0002\u0000\u0000\u0006\u0005\u0001\u0000\u0000\u0000\u0007"+
		"\n\u0001\u0000\u0000\u0000\b\u0006\u0001\u0000\u0000\u0000\b\t\u0001\u0000"+
		"\u0000\u0000\t\u000b\u0001\u0000\u0000\u0000\n\b\u0001\u0000\u0000\u0000"+
		"\u000b\r\u0005\u0005\u0000\u0000\f\u000e\u0005\u0002\u0000\u0000\r\f\u0001"+
		"\u0000\u0000\u0000\u000e\u000f\u0001\u0000\u0000\u0000\u000f\r\u0001\u0000"+
		"\u0000\u0000\u000f\u0010\u0001\u0000\u0000\u0000\u0010\u0011\u0001\u0000"+
		"\u0000\u0000\u0011\u0013\u0003\u0002\u0001\u0000\u0012\b\u0001\u0000\u0000"+
		"\u0000\u0013\u0016\u0001\u0000\u0000\u0000\u0014\u0012\u0001\u0000\u0000"+
		"\u0000\u0014\u0015\u0001\u0000\u0000\u0000\u0015\u0001\u0001\u0000\u0000"+
		"\u0000\u0016\u0014\u0001\u0000\u0000\u0000\u0017\u001a\u0005\u0004\u0000"+
		"\u0000\u0018\u0019\u0005\u0001\u0000\u0000\u0019\u001b\u0005\u0004\u0000"+
		"\u0000\u001a\u0018\u0001\u0000\u0000\u0000\u001b\u001c\u0001\u0000\u0000"+
		"\u0000\u001c\u001a\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000\u0000"+
		"\u0000\u001d \u0001\u0000\u0000\u0000\u001e \u0005\u0004\u0000\u0000\u001f"+
		"\u0017\u0001\u0000\u0000\u0000\u001f\u001e\u0001\u0000\u0000\u0000 \u0003"+
		"\u0001\u0000\u0000\u0000\u0005\b\u000f\u0014\u001c\u001f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}