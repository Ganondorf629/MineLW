var ProgressBar;

(function() {
  let progressBar = document.getElementById('progress-bar');
  let progressBarCursor = document.getElementById('progress-bar-cursor');

  ProgressBar = {
    show: function() {
      progressBar.classList.add('visible');
    },
    hide: function() {
      progressBar.classList.remove('visible');
    },
    setPercentage(percentage) {
      progressBarCursor.style.width = percentage + '%';
    },
    getPercentage() {
      return progressBarCursor.style.width;
    }
  };

})();
