package lpka.prj.crystallis.domain.symbol.data;

import lpka.prj.crystallis.domain.symbol.data.model.SymbolModel;
import lpka.prj.crystallis.domain.symbol.data.type.SymbolType;

import java.util.*;
import java.util.stream.Collectors;

public class SymbolData {
    private final Map<SymbolType, List<SymbolModel>> data;

    public SymbolData() {
        this.data = new HashMap<>();
        setup();
    }

    public Set<SymbolType> getAllSymbolTypes() {
        return data.keySet();
    }

    public Map<SymbolType, List<SymbolModel>> getSymbolModelsByTypes(Set<SymbolType> types) {
        Map<SymbolType, List<SymbolModel>> result = new HashMap<>();

        types.forEach(type -> {
            if (!result.containsKey(type)) {
                result.put(type, data.get(type));
            }
        });
        return result;
    }

    public List<SymbolModel> getSymbolModelsBySymbols(List<String> symbols) {
        return data.values().stream()
                .flatMap(Collection::stream)
                .filter(symbolModel -> symbols.stream()
                        .anyMatch(symbol -> symbol.equals(symbolModel.getSymbol())))
                .collect(Collectors.toList());
    }

    private void setup() {
        setupHiragana();
        setupKatakana();
    }

    private void setupHiragana() {
        Map<String, String> one = Map.of("あ", "a", "い", "i", "う", "u", "え", "e", "お", "o");
        Map<String, String> two = Map.of("か", "ka", "き", "ki", "く", "ku", "け", "ke", "こ", "ko");
        Map<String, String> three = Map.of("さ", "sa", "し", "shi", "す", "su", "せ", "se", "そ", "so");
        Map<String, String> four = Map.of("た", "ta", "ち", "chi", "つ", "tsu", "て", "te", "と", "to");
        Map<String, String> five = Map.of("な", "na", "に", "ni", "ぬ", "nu", "ね", "ne", "の", "no");
        Map<String, String> six = Map.of("は", "ha", "ひ", "hi", "ふ", "fu", "へ", "he", "ほ", "ho");
        Map<String, String> seven = Map.of("ま", "ma", "み", "mi", "む", "mu", "め", "me", "も", "mo");
        Map<String, String> eight = Map.of("や", "ya", "ゆ", "yu", "よ", "yo");
        Map<String, String> nine = Map.of("ら", "ra", "り", "ri", "る", "ru", "れ", "re", "ろ", "ro");
        Map<String, String> ten = Map.of("わ", "wa", "を", "wo", "ん", "n");

        Map<String, String> eleven = Map.of("が", "ga", "ぎ", "gi", "ぐ", "gu", "げ", "ge", "ご", "go");
        Map<String, String> twelve = Map.of("ざ", "za", "じ", "zi", "ず", "zu", "ぜ", "ze", "ぞ", "zo");
        Map<String, String> thirteen = Map.of("だ", "da", "ぢ", "di", "づ", "du", "で", "de", "ど", "do");
        Map<String, String> fourteen = Map.of("ば", "ba", "び", "bi", "ぶ", "bu", "べ", "be", "ぼ", "bo");
        Map<String, String> fifteen = Map.of("ぱ", "pa", "ぴ", "pi", "ぷ", "pu", "ぺ", "pe", "ぽ", "po");

        Map<String, String> sixteen = Map.of("きゃ", "kya", "きゅ", "kyu", "きょ", "kyo");
        Map<String, String> seventeen = Map.of("しゃ", "sha", "しゅ", "shu", "しょ", "sho");
        Map<String, String> eighteen = Map.of("ちゃ", "cha", "ちゅ", "chu", "ちょ", "cho");
        Map<String, String> nineteen = Map.of("にゃ", "nya", "にゅ", "nyu", "にょ", "nyo");
        Map<String, String> twenty = Map.of("ひゃ", "hya", "ひゅ", "hyu", "ひょ", "hyo");
        Map<String, String> twentyOne = Map.of("みゃ", "mya", "みゅ", "myu", "みょ", "myo");
        Map<String, String> twentyTwo = Map.of("りゃ", "rya", "りゅ", "ryu", "りょ", "ryo");

        Map<String, String> twentyThree = Map.of("ぎゃ", "gya", "ぎゅ", "gyu", "ぎょ", "gyo");
        Map<String, String> twentyFour = Map.of("じゃ", "ja", "じゅ", "ju", "じょ", "jo");
        Map<String, String> twentyFive = Map.of("びゃ", "bya", "びゅ", "byu", "びょ", "byo");
        Map<String, String> twentySix = Map.of("ぴゃ", "pya", "ぴゅ", "pyu", "ぴょ", "pyo");

        parseHiragana(one, 1);
        parseHiragana(two, 2);
        parseHiragana(three, 3);
        parseHiragana(four, 4);
        parseHiragana(five, 5);
        parseHiragana(six, 6);
        parseHiragana(seven, 7);
        parseHiragana(eight, 8);
        parseHiragana(nine, 9);
        parseHiragana(ten, 10);
        parseHiragana(eleven, 11);
        parseHiragana(twelve, 12);
        parseHiragana(thirteen, 13);
        parseHiragana(fourteen, 14);
        parseHiragana(fifteen, 15);
        parseHiragana(sixteen, 16);
        parseHiragana(seventeen, 17);
        parseHiragana(eighteen, 18);
        parseHiragana(nineteen, 19);
        parseHiragana(twenty, 20);
        parseHiragana(twentyOne, 21);
        parseHiragana(twentyTwo, 22);
        parseHiragana(twentyThree, 23);
        parseHiragana(twentyFour, 24);
        parseHiragana(twentyFive, 25);
        parseHiragana(twentySix, 26);
    }
    
    private void setupKatakana() {
        Map<String, String> one = Map.of("ア","a","イ","i","ウ","u","エ","e","オ","o");
        Map<String, String> two = Map.of("カ","ka","キ","ki","ク","ku","ケ","ke","コ","ko");
        Map<String, String> three = Map.of("サ","sa","シ","shi","ス","su","セ","se","ソ","so");
        Map<String, String> four = Map.of("タ","ta","チ","chi","ツ","tsu","テ","te","ト","to");
        Map<String, String> five = Map.of("ナ","na","ニ","ni","ヌ","nu","ネ","ne","ノ","no");
        Map<String, String> six = Map.of("ハ","ha","ヒ","hi","フ","fu","ヘ","he","ホ","ho");
        Map<String, String> seven = Map.of("マ","ma","ミ","mi","ム","mu","メ","me","モ","mo");
        Map<String, String> eight = Map.of("ヤ","ya","ユ","yu","ヨ","yo");
        Map<String, String> nine = Map.of("ラ","ra","リ","ri","ル","ru","レ","re","ロ","ro");
        Map<String, String> ten = Map.of("ワ","wa","ヲ","wo","ン","n");

        parseKatakana(one, 1);
        parseKatakana(two, 2);
        parseKatakana(three, 3);
        parseKatakana(four, 4);
        parseKatakana(five, 5);
        parseKatakana(six, 6);
        parseKatakana(seven, 7);
        parseKatakana(eight, 8);
        parseKatakana(nine, 9);
        parseKatakana(ten, 10);
    }
    
    private void parseHiragana(Map<String, String> symbols, Integer str) {
        symbols.keySet().forEach(key -> parse(SymbolType.HIRAGANA, key, symbols.get(key), str));
    }

    private void parseKatakana(Map<String, String> symbols, Integer str) {
        symbols.keySet().forEach(key -> parse(SymbolType.KATAKANA, key, symbols.get(key), str));
    }

    private void parse(SymbolType type, String symbol, String romaji, Integer str) {
        if (!data.containsKey(type)) {
            data.put(type, new ArrayList<>());
        }
        data.get(type).add(SymbolModel.of(symbol, romaji, str, type));
    }
}
