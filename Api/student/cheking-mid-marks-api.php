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
		  $i= 1;
		 
	 

		        $result_mid_exam = mysqli_query($this->connection,"select c.course_id,course_name,sum(am.marks_obtained) as 'ass_marks',sum(am.total_marks) as 'ass_total',sum(qm.obtained_marks)
                          as 'quiz_marks',sum(qm.total) as 'quiz_total',mid.midterm_marks_obtained as 'mid_marks' from assignment_marks as am,quiz_marks
                          as qm, midterm_result as mid, courses as c where am.student_history_id = '$student_history_id' and am.course_id = c.course_id
                          and qm.student_history_id='$student_history_id' and qm.course_id=c.course_id and mid.student_history_id='$student_history_id' 
                          and mid.course_id = c.course_id and c.discipline_id = '$discipline_id' and c.semester_id = '$semester_id' group by c.course_id
                          order by c.serial_no");
		       
		$midMarks['midmarks']=array();
		$total=0;
   while($show = mysqli_fetch_array($result_mid_exam)) {

            $subject_name  = $show['course_name'];
            $assignment_marks = $show['ass_marks'];
            $assignment_total_marks = $show['ass_total'];           
            $assignment_ratio = ($assignment_marks/$assignment_total_marks) * 10;
            
            $quiz_marks = $show['quiz_marks'];
            $quiz_total_marks = $show['quiz_total'];
            $quiz_ratio = ($quiz_marks/$quiz_total_marks) * 5;
            
            $mid_marks = $show['mid_marks'];
			$marks_obtained = $assignment_ratio + $quiz_ratio + $mid_marks;
			
			$total=$total+$marks_obtained ;
            
            $response=array(
						'quiz_marks'=>$quiz_ratio,
						'assignment'=>$assignment_ratio,
						'midmarks'=>$mid_marks,
						'midterm_marks'=>$marks_obtained,
						'subject'=>$subject_name,
						'total'=>$total			 
			 );
                             array_push($midMarks['midmarks'],$response);

                             }
				 
				echo json_encode($midMarks);
				mysqli_close($this -> connection);
			 
			
		}
	}
		
	 
	
	$user = new User();
	if(isset($_POST['history_id'],$_POST['discipline_id'],$_POST['semester_id'])) {
		 
		$history_id = $_POST['history_id']; 
		 
		$discipline_id = $_POST['discipline_id'];
		$semester_id = $_POST['semester_id'];
		 
	 

		//echo $discipline_id."  /  ".$session_id."  /  ".$semester_id."  /  ".$section_id."  /  ".$subject_id;exit();
		if(!empty($history_id) && !empty($discipline_id) && !empty($semester_id)){
			
		 
			$user-> AssignmentDetails($history_id,$discipline_id,$semester_id);
			
		}else{
			echo json_encode("No Record  Found");
		}
		
	}
