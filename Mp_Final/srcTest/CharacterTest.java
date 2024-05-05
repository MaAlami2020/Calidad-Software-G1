import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class CharacterTest {

    @Test
    //test unitario spy
    public void testCharacter(){
        Character character = new Character();

        Character spy = Mockito.spy(character);
        Mockito.doReturn("gholu").when(spy).getName();
        String result = spy.getName();
        verify(spy).getName();
        assertEquals("gholu",result);
    }
    @Test
    //test unitario mock
    public void testPower(){
        Character mockCharacter = mock(Character.class);
        when(mockCharacter.setPower(12)).thenReturn(12);

        EditCharacter editCharacter = new EditCharacter();
        int power = editCharacter.editPower(mockCharacter,12);
        //int result = mockCharacter.getPower();

        verify(mockCharacter).setPower(12);
        assertEquals(12,power);

    }
    @Test
    void setType() {
        Character character = new Character();
        character.setType("Vampiro");
        assertEquals(TCharacter.VAMPIRE,character.getType());

        character.setType("Cazador");
        assertEquals(TCharacter.HUNTER,character.getType());

        character.setType("Licántropo");
        assertEquals(TCharacter.WEREWOLF,character.getType());
    }
}