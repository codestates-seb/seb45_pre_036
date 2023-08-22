import { CKEditor } from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import '../styles/components/TextEditor.css';

export default function TextEditor({setBody}) {
  
  return (
    <div className="editor-container">
      <div className="text-editor">
        <CKEditor
          editor={ClassicEditor}
          data="<p>Write down something here!</p>"
          onReady={(editor) => {
            // You can store the "editor" and use when it is needed.
            console.log("Editor is ready to use!", editor);
          }}
          onChange={(event, editor) => {
            const htmlData = editor.getData();
            const parser = new DOMParser();
            const doc = parser.parseFromString(htmlData, 'text/html');
            const textData = doc.body.textContent || "";
            setBody(textData);
            // console.log({ event, editor, textData });
          }}
          onBlur={(event, editor) => {
            // console.log("Blur.", editor);
          }}
          onFocus={(event, editor) => {
            // console.log("Focus.", editor);
          }}
        />
      </div>
      {/* <div className="editor-preview">
        <h2 className="editor-preview__title">Preview:</h2>
        <div className="editor-preview__content" dangerouslySetInnerHTML={{ __html: body }} />
      </div> */}
    </div>
  );
}