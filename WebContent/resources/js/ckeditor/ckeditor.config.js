


CKEDITOR.editorConfig = function( config )
{
  config.toolbar = 'MyToolbar';

  config.toolbar_MyToolbar =
  [
      ['NewPage','Preview'],
      ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Scayt'],
      ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
      '/',
      ['Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],['Link','Unlink','Anchor'],
      '/',
      ['Bold','Italic','Underline','Strike'],
      ['Subscript','Superscript'],
      ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
      ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
      '/',
      ['Styles','Format','Font','FontSize'],
      ['TextColor','BGColor'],
      ['Maximize','ShowBlocks']
  ];

};