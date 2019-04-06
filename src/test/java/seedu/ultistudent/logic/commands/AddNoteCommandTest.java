package seedu.ultistudent.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.ultistudent.commons.core.GuiSettings;
import seedu.ultistudent.logic.CommandHistory;
import seedu.ultistudent.logic.commands.exceptions.CommandException;
import seedu.ultistudent.model.AddressBook;
import seedu.ultistudent.model.Model;
import seedu.ultistudent.model.ReadOnlyAddressBook;
import seedu.ultistudent.model.ReadOnlyUserPrefs;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.person.Person;
import seedu.ultistudent.testutil.NoteBuilder;

public class AddNoteCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddNoteCommand(null);
    }

    @Test
    public void execute_noteAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingNoteAdded modelStub = new ModelStubAcceptingNoteAdded();
        Note validNote = new NoteBuilder().build();

        CommandResult commandResult = new AddNoteCommand(validNote).execute
                (modelStub, commandHistory);

        assertEquals(String.format(AddNoteCommand.MESSAGE_SUCCESS, validNote),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validNote), modelStub.notesAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateNote_throwsCommandException() throws Exception {
        Note validNote = new NoteBuilder().build();
        AddNoteCommand addNoteCommand = new AddNoteCommand(validNote);
        ModelStub modelStub = new ModelStubWithNote(validNote);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddNoteCommand.MESSAGE_DUPLICATE_NOTE);
        addNoteCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Note draft = new NoteBuilder().withNoteName("User Guide Draft 1").build();
        Note milestone = new NoteBuilder().withNoteName("Milestone 1").build();
        AddNoteCommand addDraftCommand = new AddNoteCommand(draft);
        AddNoteCommand addMileStoneCommand = new AddNoteCommand(milestone);

        // same object -> returns true
        assertTrue(addDraftCommand.equals(addDraftCommand));

        // same values -> returns true
        AddNoteCommand addDraftCommandCopy = new AddNoteCommand(draft);
        assertTrue(addDraftCommand.equals(addDraftCommandCopy));

        // different types -> returns false
        assertFalse(addDraftCommand.equals(1));

        // null -> returns false
        assertFalse(addDraftCommand.equals(null));

        // different person -> returns false
        assertFalse(addDraftCommand.equals(addMileStoneCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Person> selectedPersonProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getSelectedPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }


        //============================================= Cap Entry =============================================//

        @Override
        public boolean hasCapEntry(CapEntry capEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCapEntry(CapEntry target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCapEntry(CapEntry capEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCapEntry(CapEntry target, CapEntry editedCapEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<CapEntry> getFilteredCapEntryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCapEntryList(Predicate<CapEntry> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<CapEntry> selectedCapEntryProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CapEntry getSelectedCapEntry() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedCapEntry(CapEntry capEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModuleSemester(ModuleSemester moduleSemester) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModuleSemester(ModuleSemester target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModuleSemester(ModuleSemester moduleSemester) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleSemester(ModuleSemester target, ModuleSemester editedCapEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ModuleSemester> getFilteredModuleSemesterList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleSemesterList(Predicate<ModuleSemester> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<ModuleSemester> selectedModuleSemesterProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ModuleSemester getSelectedModuleSemester() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedModuleSemester(ModuleSemester moduleSemester) {
            throw new AssertionError("This method should not be called.");
        }

        //============================================= Homework Manager =============================================//

        @Override
        public boolean hasHomework(Homework homework) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteHomework(Homework homework) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addHomework(Homework homework) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHomework(Homework target, Homework editedHomework) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Homework> getFilteredHomeworkList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredHomeworkList(Predicate<Homework> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Homework> selectedHomeworkProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Homework getSelectedHomework() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedHomework(Homework homework) {
            throw new AssertionError("This method should not be called.");
        }

        //======= NotesManager ===========//
        @Override
        public boolean hasNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNote(Note target, Note editedNote) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Note> getFilteredNoteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredNoteList(Predicate<Note> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Note> selectedNoteProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Note getSelectedNote() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

    }
    /**
     * A Model stub that contains a homework person.
     */
    private class ModelStubWithNote extends ModelStub {
        private final Note note;

        ModelStubWithNote(Note note) {
            requireNonNull(note);
            this.note = note;
        }

        @Override
        public boolean hasNote(Note note) {
            requireNonNull(note);
            return this.note.equals(note);
        }
    }

    /**
     * A Model stub that always accept the homework being added.
     */
    private class ModelStubAcceptingNoteAdded extends ModelStub {
        final ArrayList<Note> notesAdded = new ArrayList<>();

        @Override
        public boolean hasNote(Note note) {
            requireNonNull(note);
            return notesAdded.stream().anyMatch(note::equals);
        }

        @Override
        public void addNote(Note note) {
            requireNonNull(note);
            notesAdded.add(note);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
