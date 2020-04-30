<?php
include_once '../config/db_connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function AssignmentDetails($student_history_id,$discipline_id,$semester_id)
		{
			 $response = array();
		  $i= 0;
		 
	 

		        $result_final_exam = mysqli_query($this->connection,"select c.course_id, course_name, c.credit_hours, sum(am.marks_obtained) as 'ass_marks', sum(am.total_marks) as 'ass_total',
                            sum(qm.obtained_marks) as 'quiz_marks', sum(qm.total) as 'quiz_total', mid.midterm_marks_obtained as 'mid_marks',
                            f.finalterm_marks_obtained as 'final_marks' from assignment_marks as am, quiz_marks as qm, midterm_result as mid, 
                            finalterm_result as f, courses as c where am.student_history_id = '$student_history_id' and am.course_id = c.course_id and 
                            qm.student_history_id='$student_history_id' and qm.course_id=c.course_id and mid.student_history_id = '$student_history_id'
                            and mid.course_id = c.course_id and f.student_history_id = '$student_history_id' and f.course_id = c.course_id and 
                            c.discipline_id = '$discipline_id' and c.semester_id = '$semester_id' group by c.course_id order by c.serial_no");

		         $total_gpa = 0;
         
        $finalexam_marks = 0;
        $total_marks = 0;
        $total_crhr = 0;
        $total_marks_obtained = 0;

        $finalMarks['finalMarks']=array();
		$total=0;

        while($show = mysqli_fetch_array($result_final_exam)) {

            $assignment_marks = $show['ass_marks'];
            $assignment_total_marks = $show['ass_total'];           
            $assignment_ratio = ($assignment_marks/$assignment_total_marks) * 10;
            
            $quiz_marks = $show['quiz_marks'];
            $quiz_total_marks = $show['quiz_total'];
            $quiz_ratio = ($quiz_marks/$quiz_total_marks) * 5;
            
            $mid_marks = $show['mid_marks'];
            $midexam_marks = $assignment_ratio + $quiz_ratio + $mid_marks;
            
            $final_marks = $show['final_marks'];
            $finalexam_marks = $midexam_marks + $final_marks;
            
            $subject_credit_hr = $show['credit_hours'];
            $percentage = (round($finalexam_marks)/100) * 100;
             if($percentage >= 90 && $percentage <= 100) {
                        $grade = '&nbsp; A+';
                        $gpa = number_format(4.00, 2);
                    }
                else if ($percentage >= 80 && $percentage <= 89) {
                        $grade = 'A';
                        $gpa = number_format(3.67, 2);
                    }
                else if ($percentage >= 70 && $percentage <= 79) {
                        $grade = '&nbsp; B+';
                        $gpa = number_format(3.33, 2);
                    }
                else if ($percentage >= 65 && $percentage <= 69) {
                        $grade = 'B';
                        $gpa = number_format(3.00, 2);
                    }
                else if ($percentage >= 56 && $percentage <= 64) {
                        $grade = '&nbsp; C+';
                        $gpa = number_format(2.50, 2);
                    }
                else if ($percentage >= 50 && $percentage <= 55) {
                        $grade = 'C';
                        $gpa = number_format(2.00, 2);
                    }
                else if ($percentage >= 40 && $percentage <= 49) {
                        $grade = 'D';
                        $gpa = number_format(1.30, 2);
                    }
                else if ($percentage >= 0 && $percentage <= 39) {
                        $grade = 'E';
                        $gpa = number_format(0.00, 2);
                    }                
                 
$i++;
 
        $c_id =  $show['course_id'];  
         $c_name =  $show['course_name'];  
         $mid_markss =  round($midexam_marks); 
         $final_total_marks_api =  round($final_marks);  
        $final_api_marks = round($finalexam_marks); 
         
         round($percentage)." %";  
          $grade;  
          $gpa;  
          $total_gpa=$total_gpa+$gpa;
          $total=$total+$final_api_marks;

            $response=array(
						'final_marks'=>$final_api_marks,
						'gpa'=>$gpa,
                        'subject'=>$c_name,
                        'total_gpa'=>($total_gpa)/$i,
                        'total_marks'=>	$total	 
			 );
                             array_push( $finalMarks['finalMarks'],$response);

                             }
				 
				echo json_encode($finalMarks);
				mysqli_close($this -> connection);
			 
			
		}
	}
		
	 
	
	$user = new User();
	if(isset($_POST['student_history_id'],$_POST['discipline_id'],$_POST['semester_id'])) {
		 
		$history_id = $_POST['student_history_id']; 
		 
		$discipline_id = $_POST['discipline_id'];
		$semester_id = $_POST['semester_id'];
		 
	 

		//echo $discipline_id."  /  ".$session_id."  /  ".$semester_id."  /  ".$section_id."  /  ".$subject_id;exit();
		if(!empty($history_id) && !empty($discipline_id) && !empty($semester_id)){
			
		 
			$user-> AssignmentDetails($history_id,$discipline_id,$semester_id);
			
		}else{
			echo json_encode("No Record  Found");
		}
		
	}
