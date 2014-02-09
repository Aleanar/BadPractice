if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

$(document).ready(function() {
    $('#thread-editor').summernote({
        height: '300px'
    });
    $('#post-editor').summernote();
    $('#comment-editor').summernote({
        height: '100px',
        toolbar: [
            ['style', ['bold', 'italic', 'underline', 'clear']]
        ]
    });
});