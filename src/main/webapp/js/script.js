// Script for task management system

         document.addEventListener('DOMContentLoaded', function() {
             // Auto-hide alerts after 5 seconds
             const alerts = document.querySelectorAll('.alert');
             alerts.forEach(function(alert) {
                 setTimeout(function() {
                     alert.style.opacity = '0';
                     setTimeout(function() {
                         alert.style.display = 'none';
                     }, 500);
                 }, 5000);
             });

             // Add date validation
             const dueDateInput = document.getElementById('dueDate');
             if (dueDateInput) {
                 // Set min date to today
                 const today = new Date();
                 const dd = String(today.getDate()).padStart(2, '0');
                 const mm = String(today.getMonth() + 1).padStart(2, '0');
                 const yyyy = today.getFullYear();
                 const todayStr = yyyy + '-' + mm + '-' + dd;

                 // Only set min date for new tasks (not for editing existing tasks)
                 if (!document.querySelector('input[name="id"]')) {
                     dueDateInput.setAttribute('min', todayStr);
                 }
             }
         });