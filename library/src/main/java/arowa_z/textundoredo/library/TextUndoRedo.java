package arowa_z.textundoredo.library;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 实现文本撤销重做功能
 * Created by Arowa_Z on 2016/4/26.
 */
public class TextUndoRedo implements TextWatcher {
    public interface TextChangeInfo {
        void textAction();
    }

    private class Record {
        private int start;
        private int end;
        private CharSequence text;
        private Record prior;
        private Record next;

        Record(int start, int end, CharSequence text) {
            this.start = start;
            this.end = end;
            this.text = text;
            if (offset != null) {
                offset.next = this;
                prior = offset;
            }
            offset = this;
        }
    }

    private Record offset;
    private Editable editable;
    private TextChangeInfo info;
    private boolean isUndoOrRedo;

    public TextUndoRedo(EditText editText, TextChangeInfo info) {
        editText.addTextChangedListener(this);
        this.editable = editText.getEditableText();
        this.info = info;
        new Record(0, 0, null);
    }

    public void exeUndo() {
        exeUndoOrRedo(true);
    }

    public void exeRedo() {
        exeUndoOrRedo(false);
    }

    public boolean canUndo() {
        return offset.prior != null;
    }

    public boolean canRedo() {
        return offset.next != null;
    }

    public void cleanRecord(){
        cleanPrior();
        cleanNext();
    }

    //==============================================================================================

    private void noticeTextChang(){
        if(info != null){
            info.textAction();
        }
    }

    private void cleanNext() {
        while (offset.next != null) {
            Record record = offset.next;
            offset.next = record.next;
            record.prior = null;
            record.next = null;
        }
    }

    private void cleanPrior() {
        while (offset.prior != null) {
            Record record = offset.prior;
            offset.prior = record.prior;
            record.prior = null;
            record.next = null;
        }
    }

    private void exeUndoOrRedo(boolean Or) {
        if (!Or) {
            offset = offset.next;
        }
        isUndoOrRedo = true;
        CharSequence temp = editable.subSequence(offset.start, offset.end);
        editable.replace(offset.start, offset.end, offset.text);
        offset.end = offset.start + offset.text.length();
        Selection.setSelection(editable, offset.end);
        offset.text = temp;
        isUndoOrRedo = false;
        if (Or) {
            offset = offset.prior;
        }
        noticeTextChang();
    }

    @Deprecated
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (isUndoOrRedo) {
            return;
        }
        new Record(start, start + after, s.subSequence(start, start + count));
        cleanNext();
        noticeTextChang();
    }

    @Deprecated
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Deprecated
    @Override
    public void afterTextChanged(Editable s) {
    }
}
