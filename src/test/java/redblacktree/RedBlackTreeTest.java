package redblacktree;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RedBlackTreeTest {
    private RedBlackTree<String> tree = new RedBlackTree<>();

    @Test
    void getShouldReturnValue_ifFound() {
        //given
        tree.put(1, "abracadabra");
        //when
        var res = tree.get(1);
        //then
        assertEquals(res, "abracadabra");
    }

    @Test
    void getShouldReturnNull_ifNotFound() {
        //when
        var res = tree.get(1);
        //then
        assertNull(res);
    }

    @Test
    void deleteShouldReturnNull_ifNotFound() {
        //when
        var res = tree.remove(1);
        //then
        assertNull(res);
    }

    @Test
    void deleteShouldReturnValue_ifFound() {
        //given
        tree.put(1, "abra");
        //when
        var res = tree.remove(1);
        //then
        assertEquals(res, "abra");
        assertNull(tree.get(1));
    }

    @Test
    void firstKeyShouldReturnNull_ifTreeSizeIsZero() {
        //when
        var res = tree.firstKey();
        //then
        assertNull(res);
    }

    @Test
    void firstKeyShouldReturnMaxKeyValue_ifTreeSizeMoreZero() {
        //given
        tree.put(20, "20");
        tree.put(25, "25");
        tree.put(23, "23");
        tree.put(30, "30");
        tree.put(10, "10");
        //when
        var res = tree.firstKey();
        //then
        assertEquals(res, 30);
    }

    @Test
    void lastKeyShouldReturnNull_ifTreeSizeIsZero() {
        //when
        var res = tree.lastKey();
        //then
        assertNull(res);
    }

    @Test
    void lastKeyShouldReturnMinKeyValue_ifTreeSizeMoreZero() {
        //given
        tree.put(1, "abra");
        //when
        var res = tree.lastKey();
        //then
        assertEquals(res, 1);
    }

    @Test
    @Disabled
    void tailMapShouldReturnNull_ifKeyNotFound(){
        //when
        var res = tree.tailMap(1);
        //then
        assertNull(res);
    }

    @Test
    void tailMapShouldReturnNewTreeWithKeysLessThenKey_ifKeyFound(){
        //given
        tree.put(16, "16");
        tree.put(14, "14");
        tree.put(12, "12");
        tree.put(15, "15");
        tree.put(11, "11");
        tree.put(17, "17");
        tree.put(19, "19");
        //when
        var res = tree.tailMap(16);
        assertEquals(res.get(16), "16");
        assertEquals(res.get(17), "17");
        assertEquals(res.get(19), "19");

    }
    @Test
    @Disabled
    void headMapShouldReturnNull_ifKeyNotFound(){
        //when
        var res = tree.headMap(16);
        //then
        assertNull(res);
    }
    @Test
    void headMapShouldReturnKeysMoreThenKey_ifFound(){
        //given
        tree.put(16, "16");
        tree.put(14, "14");
        tree.put(12, "12");
        tree.put(15, "15");
        tree.put(11, "11");
        tree.put(17, "17");
        tree.put(19, "19");
        //when
        var res = tree.headMap(16);
        //then
        assertEquals(res.get(16), "16");
        assertEquals(res.get(15), "15");
        assertEquals(res.get(14), "14");
        assertEquals(res.get(12), "12");
        assertEquals(res.get(11), "11");
    }

    @Test
    @Disabled
    void subMapShouldReturnNull_ifKeysNotFound(){
        //when
        var res = tree.subMap(16, 23);
        //then
        assertNull(res);
    }
    @Test
    void subMapShouldReturnTreeMapWithKeysBetweenFirstKeyAndSecondKey(){
        //given
        tree.put(16, "16");
        tree.put(14, "14");
        tree.put(12, "12");
        tree.put(15, "15");
        tree.put(11, "11");
        tree.put(17, "17");
        tree.put(19, "19");
        //when
        var res = tree.subMap(14,16);
        //then
        assertEquals(res.get(16), "16");
        assertEquals(res.get(15), "15");
        assertEquals(res.get(14), "14");
    }
    @Test
    void firstEntryShouldReturnNull_ifTreeSizeIsZero(){
        //when
        var res = tree.firstEntry();
        //then
        assertNull(res);
    }

    @Test
    void firstEntryShouldReturnEntryWithMaxKey_ifTreeSizeMoreZero(){
        //given
        tree.put(15,"d");
        //when
        var res = tree.firstEntry();
        //then
        assertEquals(res.value, "d");
        assertEquals(res.key, 15);
    }
    @Test
    void lastEntryShouldReturnNull_ifTreeSizeIsZero(){
        //when
        var res = tree.lastEntry();
        //then
        assertNull(res);
    }

    @Test
    void lastEntryShouldReturnEntryWithMinKey_ifTreeSizeMoreZero(){
        //given
        tree.put(15,"d");
        //when
        var res = tree.lastEntry();
        //then
        assertEquals(res.value, "d");
        assertEquals(res.key, 15);
    }
    @Test
    void pollFirstShouldReturnNull_ifTreeSizeIsZero(){
        //when
        var res = tree.pollFirst();
        //then
        assertNull(res);
    }
    @Test
    void pollFirstShouldReturnAndRemoveEntryWithMaxKey_ifTreeSizeMoreZero(){
        //given
        tree.put(16,"dd");
        //when
        var res = tree.pollFirst();
        //given
        assertEquals(res.value, "dd");
        assertEquals(res.key, 16);
        assertNull(tree.get(16));
    }
    @Test
    void pollLastShouldReturnAndRemoveEntryWithMinKey_ifTreeSizeMoreZero(){
        //given
        tree.put(16,"dd");
        //when
        var res = tree.pollLast();
        //given
        assertEquals(res.value, "dd");
        assertEquals(res.key, 16);
        assertNull(tree.get(16));
    }

    @Test
    void ceilingEntryShouldReturnNull_ifNotFound(){
        //when
        var res = tree.ceilingEntry(4);
        //then
        assertNull(res);
    }
    @Test
    void ceilingEntryShouldReturnEntryWithEqualKey_ifFound(){
        //given
        tree.put(4,"4");
        tree.put(5,"5");
        tree.put(8,"8");

        //when
        var res = tree.ceilingEntry(5);
        //then
        assertEquals(res.key, 5);
        assertEquals(res.value, "5");
    }
    @Test
    void ceilingEntryShouldReturnEntryWithBiggerKey_ifKeyNotFoundButTreeContainsBiggerKeys(){
        //given
        tree.put(4,"4");
        tree.put(5,"5");
        tree.put(8,"8");

        //when
        var res = tree.ceilingEntry(6);
        //then
        assertEquals(res.key, 8);
        assertEquals(res.value, "8");
    }

    @Test
    void ceilingKeyShouldReturnNull_ifNotFound(){
        //when
        var res = tree.ceilingKey(4);
        //then
        assertNull(res);
    }
    @Test
    void ceilingKeyShouldReturnKeyWithEqualKey_ifFound(){
        //given
        tree.put(4,"4");
        tree.put(5,"5");
        tree.put(8,"8");

        //when
        var res = tree.ceilingKey(5);
        //then
        assertEquals(res, 5);

    }
    @Test
    void ceilingKeyShouldReturnBiggerKey_ifKeyNotFoundButTreeContainsBiggerKeys(){
        //given
        tree.put(4,"4");
        tree.put(5,"5");
        tree.put(8,"8");

        //when
        var res = tree.ceilingKey(6);
        //then
        assertEquals(res, 8);
    }



}
