package arowa_z.textundoredo.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import arowa_z.textundoredo.library.TextUndoRedo;

public class MainActivity extends Activity implements
        TextUndoRedo.TextChangeInfo,
        View.OnClickListener {
    private TextUndoRedo TUR;
    private Button btn_undo;
    private Button btn_redo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_undo = (Button) findViewById(R.id.btn_undo);
        btn_undo.setOnClickListener(this);
        btn_redo = (Button) findViewById(R.id.btn_redo);
        btn_redo.setOnClickListener(this);
        findViewById(R.id.btn_clean).setOnClickListener(this);

        TUR = new TextUndoRedo((EditText) findViewById(R.id.et_area), this);
        textAction();
    }

    @Override
    public void textAction() {
        btn_undo.setEnabled(TUR.canUndo());
        btn_redo.setEnabled(TUR.canRedo());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_undo:
                TUR.exeUndo();
                break;
            case R.id.btn_redo:
                TUR.exeRedo();
                break;
            case R.id.btn_clean:
                TUR.cleanRecord();
                textAction();
                break;
        }
    }
}
