<?php
include_once '../config/db_connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function AssignmentDetails($history_id,$course_code)
		{
			 $response = array();
		  $i= 1;
		 
	 

		        $result_assignment = mysqli_query($this->connection,"SELECT * from quiz_marks where student_history_id = '$history_id' and
                             course_id = '$course_code'");
        $marks['marks']=array();
  while($show = mysqli_fetch_array($result_assignment)) {
            
            $mo = $show['obtained_marks'];
            $ass_no = $show['quiz_number'];
			$topic = $show['topic_name'];
			
             
                     $response=array(
						'marks_obtained'=>$mo,
						'quiz_no'=>$ass_no,
						'topic' =>$topic				 
			 );
                             
            array_push($marks['marks'],$response);
                             }
				 
				echo json_encode($marks);
				mysqli_close($this -> connection);
			 
			
		}
	}
		
	 
	
	$user = new User();
	if(isset($_POST['course_code'],$_POST['history_id'])) {
		 
		 
		$course_code = $_POST['course_code'];
		$history_id = $_POST['history_id'];
		 
	 

		//echo $discipline_id."  /  ".$session_id."  /  ".$semester_id."  /  ".$section_id."  /  ".$subject_id;exit();
		if(!empty($course_code) && !empty($history_id)){
			
		 
			$user-> AssignmentDetails($history_id,$course_code);
			
		}else{
			echo json_encode("No Record  Found");
		}
		
	}
