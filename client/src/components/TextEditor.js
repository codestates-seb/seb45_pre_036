import { CKEditor } from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import '../styles/components/TextEditor.css';
import { useState } from "react";

export default function TextEditor() {
  const [body, setBody]=useState('');

  return (
    <div className="editor-container">
      <div className="text-editor">
        <CKEditor
          editor={ClassicEditor}
          data="<p>Write down something here&nbsp;5!</p>"
          onReady={(editor) => {
            // You can store the "editor" and use when it is needed.
            console.log("Editor is ready to use!", editor);
          }}
          onChange={(event, editor) => {
            const data = editor.getData();
            setBody(data);
            console.log({ event, editor, data });
          }}
          onBlur={(event, editor) => {
            console.log("Blur.", editor);
          }}
          onFocus={(event, editor) => {
            console.log("Focus.", editor);
          }}
        />
      </div>
      <div className="editor-preview">
        <h2 className="editor-preview__title">Preview:</h2>
        <div className="editor-preview__content" dangerouslySetInnerHTML={{ __html: body }} />
      </div>
    </div>
  );
}